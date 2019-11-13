package com.kirinsidea.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivitySplashBinding;
import com.kirinsidea.ui.BaseActivity;
import com.kirinsidea.ui.login.LoginActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * TODO 1. 들어가는 중... 텍스트 애니메이션 + 땀방울 애니메이션
 * TODO 2. 로딩 최소시간 (2초) 타이머
 * TODO 3. 유저 로그인 여부/ 토큰 만료 여부 확인
 * TODO 4. 북마크 리스트 미리 로딩
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadingTimer();
    }

    @SuppressLint("CheckResult")
    private void initLoadingTimer() {
        Completable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> startMainActivity());
    }

    private void startMainActivity() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
