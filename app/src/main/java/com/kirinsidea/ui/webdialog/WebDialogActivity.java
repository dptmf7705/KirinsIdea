package com.kirinsidea.ui.webdialog;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivityWebDialogBinding;
import com.kirinsidea.extension.Injection;
import com.kirinsidea.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class WebDialogActivity extends BaseActivity<ActivityWebDialogBinding> implements WebNavigator{

    private String TAG = "WebDialogActivity";
    private String Connecturl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initViews();
    }

    private void initViewModel(){
        binding.setVm(Injection.provideAddNewBookmarkViewModel(this));
        binding.getVm().setNavigator(this);
    }
    private void initViews() {
        binding.btnKeep.setOnClickListener(__ -> getWebUrl());
    }

    @Override
    public String getWebUrl(){
        final Intent intent = getIntent();
        final String action = intent.getAction();
        final String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String text = intent.getStringExtra(Intent.EXTRA_TEXT);
                Connecturl = findUrlInText(text);
                Log.d(TAG, "get connect url: " + Connecturl);
            }
        }
        return Connecturl;
    }
    private String findUrlInText(String text) {
        List<String> urls = new ArrayList<>();
        Matcher m = Patterns.WEB_URL.matcher(text);
        while (m.find()) {
            String url = m.group();
            urls.add(url);
        }
        if (urls.size() > 0) {
            return urls.get(0);
        }
        return null;
    }
    @Override
    public void finishWebDialog(){
        finish();
    }
}
