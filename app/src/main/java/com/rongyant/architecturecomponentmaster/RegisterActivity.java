package com.rongyant.architecturecomponentmaster;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.rongyant.architecturecomponentmaster.base.BaseActivity;
import com.rongyant.architecturecomponentmaster.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.registerToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int setContentView() {
        return R.layout.activity_register;
    }


}
