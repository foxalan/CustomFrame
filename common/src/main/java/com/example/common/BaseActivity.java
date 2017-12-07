package com.example.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * 1.setContentView
 * 2.initView
 * 3.处理OnBackPressed
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentID());
    }

    public abstract int getContentID();

    protected void initView(){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment:fragmentList){
            if (fragment instanceof BaseFragment){
                if (((BaseFragment) fragment).onBackPressed()){
                    finish();
                }else {

                }
            }
        }

        finish();
    }
}
