package com.example.alan.customframe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.alan.customframe.activity.ProxyActivity;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.TestDelegate;
import com.example.alan.customframe.delegate.home.HomeDelegate;
import com.example.alan.customframe.delegate.sign.LoginDelegate;
import com.example.alan.customframe.login.ISignCallBack;
import com.example.alan.customframe.util.LogUtil;

/**
 * @author Alan
 */

public class MainActivity extends ProxyActivity implements ISignCallBack{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new HomeDelegate();
    }


    @Override
    public void onSignIn() {
        LogUtil.e("sign success");
        start(new TestDelegate());
    }

    @Override
    public void onSignOut() {
        LogUtil.e("have not sign success");
        start(new LoginDelegate());
    }
}
