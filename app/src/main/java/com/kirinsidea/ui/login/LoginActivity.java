package com.kirinsidea.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.data.source.remote.thirdparty.facebook.FacebookLoginHelper;
import com.kirinsidea.data.source.remote.thirdparty.google.GoogleLoginHelper;
import com.kirinsidea.databinding.ActivityLoginBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.base.BaseActivity;
import com.kirinsidea.ui.main.MainActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private GoogleLoginHelper googleLoginHelper;
    private FacebookLoginHelper facebookLoginHelper;

    public static Intent getLaunchIntent(@NonNull Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("로그인");
        initLoginHelpers();
        initViewModel();
        initViews();
    }

    private void initLoginHelpers() {
        googleLoginHelper = GoogleLoginHelper.with(this);
        facebookLoginHelper = FacebookLoginHelper.with(binding.btnFacebookGone);
    }

    private void initViewModel() {
        binding.setVm(Providers.getViewModel(this, LoginViewModel.class));
        binding.getVm().setGoogleLoginHelper(googleLoginHelper);
        binding.getVm().setFacebookLoginHelper(facebookLoginHelper);
        binding.getVm().getLoginSuccess().observe(this, success -> {
            if (success) navigateLoginSuccess();
        });
    }

    private void initViews() {
        binding.btnFacebook.setOnClickListener(__ -> binding.btnFacebookGone.performClick());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        binding.getVm().onActivityResult(requestCode, resultCode, data);
    }

    public void navigateLoginSuccess() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
