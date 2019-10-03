package com.kirinsidea.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    @NonNull
    private final MutableLiveData<String> inputEmail = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> inputPassword = new MutableLiveData<>();

    @NonNull
    private LoginRepository repository;

    private LoginViewModel(@NonNull final LoginRepository repository) {
        this.repository = repository;
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


    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final LoginRepository repository;

        public Factory(@NonNull final LoginRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(LoginViewModel.class)) {
                //noinspection unchecked
                return (T) new LoginViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }
}
