package com.kirinsidea.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gun0912.tedonactivityresult.model.ActivityResult;
import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivityLoginBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.base.BaseActivity;
import com.kirinsidea.ui.main.MainActivity;
import com.tedpark.tedonactivityresult.rx2.TedRxOnActivityResult;

import io.reactivex.Single;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginNavigator {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initViews();
    }

    private void initViewModel() {
        binding.setVm(Providers.getViewModel(this, LoginViewModel.class));
        binding.getVm().setNavigator(this);
    }

    private void initViews() {
        binding.layoutPassword.btnNext.setOnClickListener(__ -> navigateLoginSuccess());
    }

    @Override
    public Single<Intent> navigateLoginWithGoogle(@NonNull final Intent intent) {
        return TedRxOnActivityResult.with(this)
                .startActivityForResult(intent)
                .map(ActivityResult::getData);
    }

    @Override
    public void navigateLoginWithEmail() {
        binding.layoutLogin.getRoot().setVisibility(View.GONE);
        binding.layoutPassword.getRoot().setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateLoginSuccess() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
