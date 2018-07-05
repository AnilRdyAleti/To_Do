package com.example.anilreddy.to_do_project.Main;

import android.support.v4.app.Fragment;

import com.example.anilreddy.to_do_project.R;
import com.example.anilreddy.to_do_project.appDefault.BaseFragment;

public class MainFragment extends BaseFragment {
    @Override
    protected int layoutRes() {
        return R.layout.fragment_main;
    }

    public static Fragment newInstance() {
        return null;
    }
}
