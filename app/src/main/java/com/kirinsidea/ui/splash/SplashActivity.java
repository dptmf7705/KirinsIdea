package com.kirinsidea.ui.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.databinding.ActivitySplashBinding;
import com.kirinsidea.ui.base.BaseActivity;
import com.kirinsidea.ui.login.LoginActivity;
import com.kirinsidea.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModels();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.getSplashVm().resumeTimer();
    }

    @Override
    protected void onPause() {
        binding.getSplashVm().pauseTimer();
        super.onPause();
    }

    private void initViewModels() {
        binding.setSplashVm(Providers.getViewModel(this, SplashViewModel.class));
        binding.getSplashVm().startTimer();
        binding.getSplashVm().getConfig().observe(this, config -> {
            if (config.isLoggedIn()) {
                startMainActivity();
            } else {
                startLoginActivity();
            }
        });
    }

    private void startMainActivity() {
        startActivity(MainActivity.getLaunchIntent(this));
        finish();
    }

    private void startLoginActivity() {
        startActivity(LoginActivity.getLaunchIntent(this));
        finish();
    }
}
