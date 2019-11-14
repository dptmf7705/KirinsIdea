package com.kirinsidea.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kirinsidea.data.repository.BaseRepository;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel<N> extends ViewModel {

    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    @NonNull
    protected final MutableLiveData<? super Throwable> error = new MutableLiveData<>();

    protected WeakReference<N> navigator;

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    @NonNull
    public abstract BaseViewModel init(@NonNull final BaseRepository... repositories);
}