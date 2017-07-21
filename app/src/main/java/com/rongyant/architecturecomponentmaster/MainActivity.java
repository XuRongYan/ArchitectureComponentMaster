package com.rongyant.architecturecomponentmaster;

import android.os.Bundle;

import com.rongyant.architecturecomponentmaster.base.BaseActivity;
import com.rongyant.architecturecomponentmaster.databinding.ActivityMainBinding;
import com.rongyant.architecturecomponentmaster.ui.MainAtyClickCallback;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainAtyClickCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.mainToolbar);
        binding.setCallback(this);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onRegisterClick() {
        goActivity(RegisterActivity.class);
    }

    @Override
    public void onForgetPswClick() {

    }

    @Override
    public void onLoginClick() {

    }
}
