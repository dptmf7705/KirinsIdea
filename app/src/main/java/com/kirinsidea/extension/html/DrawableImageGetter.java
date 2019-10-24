package com.kirinsidea.extension.html;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kirinsidea.App;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.CompletableSubject;
import io.reactivex.subjects.PublishSubject;

public class DrawableImageGetter implements Html.ImageGetter {
    @NonNull
    private final PublishSubject<Boolean> imageLoadingStream = PublishSubject.create();

    @Override
    public Drawable getDrawable(@Nullable String source) {
        DrawablePlaceHolder drawable = new DrawablePlaceHolder(imageLoadingStream);

        Glide.with(App.instance().getContext())
                .load(source)
                .skipMemoryCache(true)
                .listener(drawable)
                .submit();

        return drawable;
    }

    @NonNull
    public Observable<Boolean> getImageLoadingStream() {
        return imageLoadingStream.subscribeOn(Schedulers.io()).serialize();
    }


    private static class DrawablePlaceHolder extends Drawable implements RequestListener<Drawable> {
        @NonNull
        private final PublishSubject<Boolean> imageLoadingStream;

        private Drawable drawable;

        private DrawablePlaceHolder(@NonNull final PublishSubject<Boolean> imageLoadingStream) {
            this.imageLoadingStream = imageLoadingStream;
        }

        @Override
        public void draw(@NonNull final Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(@NonNull final Drawable drawable) {
            this.drawable = drawable;
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();

            drawable.setBounds(0, 0, width, height);
            setBounds(0, 0, width, height);

            this.imageLoadingStream.onNext(true);
        }

        @Override
        public boolean onResourceReady(Drawable resource,
                                       Object model,
                                       Target<Drawable> target,
                                       DataSource dataSource,
                                       boolean isFirstResource) {
            setDrawable(resource);
            return false;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e,
                                    Object model,
                                    Target target,
                                    boolean isFirstResource) {
            return false;
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }
    }
}