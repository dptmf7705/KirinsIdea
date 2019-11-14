package com.kirinsidea.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    @NonNull
    private final MutableLiveData<String> inputEmail = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> inputPassword = new MutableLiveData<>();

    private LoginRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.repository = (LoginRepository) repositories[0];
        return this;
    }

    public void startLoginWithGoogle() {
        addDisposable(repository.observeGoogleLoginIntent()
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(navigator.get()::navigateLoginWithGoogle)
                .flatMapCompletable(repository::observeLoginWithGoogle)
                .subscribe(navigator.get()::navigateLoginSuccess,
                        error::setValue));
    }

    public void startLoginWithEmail() {
        // TODO. 이메일 형식 맞는지 체크
        navigator.get().navigateLoginWithEmail();
    }

    public void checkPassword() {
        navigator.get().navigateLoginSuccess();
    }

    @NonNull
    public MutableLiveData<String> getInputEmail() {
        return inputEmail;
    }

    @NonNull
    public MutableLiveData<String> getInputPassword() {
        return inputPassword;
    }
}
