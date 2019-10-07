package com.kirinsidea.extension;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.kirinsidea.R;
import com.kirinsidea.data.repository.BookmarkRepositoryImpl;
import com.kirinsidea.data.repository.FolderRepositoryImpl;
import com.kirinsidea.data.repository.LoginRepositoryImpl;
import com.kirinsidea.data.source.local.room.AppDatabase;
import com.kirinsidea.data.source.local.room.dao.BaseDao;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.google.GoogleLoginApi;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;
import com.kirinsidea.ui.bookmarklist.BookmarkListViewModel;
import com.kirinsidea.ui.login.LoginViewModel;
import com.kirinsidea.ui.webdialog.AddNewBookmarkViewModel;

public class Injection{
    public interface Type {
        String Login = "Login";
        String Bookmark = "Bookmark";
        String BookmarkList = "BookmarkList";
        String AddNewBookmark = "AddNewBookmark";
        String Folder = "Folder";
    }

    @NonNull
    public static <VM extends BaseViewModel> VM provideViewModel(
            @NonNull final FragmentActivity activity,
            @NonNull final Class<VM> vmClass){

        return ViewModelProviders.of(activity).get(vmClass);
    }

    @NonNull
    private static <T extends BaseRepository> T provideBaseRepository(@NonNull final Context context, String type) {
        switch (type) {
            case Type.Login:
                //noinspection unchecked
                return (T) LoginRepositoryImpl.getInstance(
                        provideGoogleLoginApi(context),
                        provideFirebaseAuthApi());
            case Type.Bookmark:
                //noinspection unchecked
                return (T) BookmarkRepositoryImpl.getInstance(
                        provideBaseDao(context, Type.Bookmark), provideRetrofitClient());
            case Type.Folder:
                //noinspection unchecked
                return (T) FolderRepositoryImpl.getInstance(
                        provideRetrofitClient(),provideBaseDao(context, Type.Folder));
        }
        return null;
    }

    @NonNull
    private static <T extends BaseDao> T provideBaseDao(@NonNull final Context context, String type){
        switch (type){
            case Type.Bookmark:
                //noinspection unchecked
                return (T) provideRoomDatabase(context).bookmarkDao();
            case Type.Folder:
                //noinspection unchecked
                return (T) provideRoomDatabase(context).folderDao();
        }
        return null;
    }

    @NonNull
    private static GoogleLoginApi provideGoogleLoginApi(@NonNull final Context context) {
        return GoogleLoginApi.getInstance(provideGoogleSignInClient(context));
    }

    @NonNull
    private static FirebaseAuthApi provideFirebaseAuthApi() {
        return FirebaseAuthApi.getInstance();
    }
    @NonNull
    private static RetrofitClient provideRetrofitClient() {
        return RetrofitClient.getInstance();
    }
    @NonNull
    private static AppDatabase provideRoomDatabase(
            @NonNull final Context context) {

        return AppDatabase.getDatabase(context);
    }

    @NonNull
    private static GoogleSignInClient provideGoogleSignInClient(
            @NonNull final Context context) {

        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        return GoogleSignIn.getClient(context.getApplicationContext(), options);
    }
}
