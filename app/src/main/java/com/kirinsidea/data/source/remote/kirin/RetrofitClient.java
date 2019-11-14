package com.kirinsidea.data.source.remote.kirin;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kirinsidea.BuildConfig;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://133.186.146.239:5000/";

    private static class LazyHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    @NonNull
    public static RetrofitClient getInstance() {
        return LazyHolder.INSTANCE;
    }

    @NonNull
    private final Retrofit retrofit;

    private RetrofitClient() {
        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        setHeader(httpClientBuilder);
        setLogger(httpClientBuilder);
        setErrorHandler(httpClientBuilder);
        setTimeout(httpClientBuilder);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(Boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapter(RetrofitResultCode.class, new RetrofitResultCodeAdapter())
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();
    }

    /**
     * Boolean type Adapter
     */
    private static class BooleanTypeAdapter implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {
        public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return "true".equals(json.getAsString());
        }

        @Override
        public JsonElement serialize(Boolean src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Boolean.TRUE.equals(src) ? "true" : "false");
        }
    }

    /**
     * Boolean type Adapter
     */
    private static class RetrofitResultCodeAdapter implements JsonDeserializer<RetrofitResultCode> {
        @Override
        public RetrofitResultCode deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return RetrofitResultCode.get(json.getAsInt());
        }
    }

    /**
     * Header 설정
     */
    private void setHeader(OkHttpClient.Builder builder) {
        builder.addInterceptor(chain ->
                chain.proceed(chain.request().newBuilder()
//                        .header("UserToken", "")
                        .build()));
    }

    /**
     * Logger 설정
     */
    private void setLogger(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }
    }

    /**
     * ErrorHandler 설정
     */
    private void setErrorHandler(OkHttpClient.Builder builder) {
        // TODO custom error handling
    }

    /**
     * Timeout 설정
     */
    private void setTimeout(OkHttpClient.Builder builder) {
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
    }

    public <T> T create(Class<T> apiClass) {
        return retrofit.create(apiClass);
    }
}
