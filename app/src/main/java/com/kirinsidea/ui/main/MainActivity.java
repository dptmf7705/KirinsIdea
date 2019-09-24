package com.kirinsidea.ui.main;

import android.os.Bundle;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivityMainBinding;
import com.kirinsidea.ui.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
