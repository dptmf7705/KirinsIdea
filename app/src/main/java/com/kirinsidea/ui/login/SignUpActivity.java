package com.kirinsidea.ui.login;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivitySignUpBinding;
import com.kirinsidea.ui.base.BaseActivity;

import java.util.Calendar;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> implements DatePickerDialog.OnDateSetListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("회원가입");
        initViews();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        binding.etInputBirthday.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(this,
                        R.style.CalendarTheme,
                        this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        binding.etInputBirthday.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
    }
}
