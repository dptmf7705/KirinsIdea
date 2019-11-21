package com.kirinsidea.ui.splash;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.config.ConfigRepository;
import com.kirinsidea.data.source.entity.ConfigEntity;
import com.kirinsidea.ui.base.BaseViewModel;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class SplashViewModel extends BaseViewModel {
    private static final long TIME_OUT = 2000;

    @NonNull
    private final MutableLiveData<AtomicLong> counter = new MutableLiveData<>(new AtomicLong());
    @NonNull
    private final MutableLiveData<ConfigEntity> config = new MutableLiveData<>();

    @NonNull
    private final BehaviorSubject<Boolean> timer = BehaviorSubject.create();

    private ConfigRepository configRepository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.configRepository = (ConfigRepository) repositories[0];
        return this;
    }

    void startTimer() {
        addDisposable(Single
                .zip(observeTimer(),
                        configRepository.observeDefaultConfig(),
                        (timer, config) -> config)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(config::setValue, error::setValue));
    }

    @NonNull
    private Single<Long> observeTimer() {
        return timer.distinctUntilChanged()
                .switchMap(on -> {
                    if (on) {
                        return Observable.interval(1L, TimeUnit.MILLISECONDS, Schedulers.computation());
                    } else {
                        return Observable.never();
                    }
                })
                .map(t -> counter.getValue().getAndIncrement())
                .takeWhile(t -> t <= TIME_OUT)
                .filter(t -> t == TIME_OUT)
                .single(TIME_OUT)
                .subscribeOn(Schedulers.computation());
    }

    void pauseTimer() {
        timer.onNext(false);
    }

    void resumeTimer() {
        timer.onNext(true);
    }

    @NonNull
    public LiveData<ConfigEntity> getConfig() {
        return config;
    }
}
