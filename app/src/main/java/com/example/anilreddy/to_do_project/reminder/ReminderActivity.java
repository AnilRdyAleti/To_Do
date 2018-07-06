package com.example.anilreddy.to_do_project.reminder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.anilreddy.to_do_project.R;
import com.example.anilreddy.to_do_project.appDefault.BaseActivity;

public class ReminderActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return ReminderFragment.newInstance();
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.reminder_layout;
    }
}
