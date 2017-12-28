package com.example.alan.customframe.delegate.sign;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function : 登录页面
 * Modify Date : 2017/12/28
 * Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class LoginDelegate extends LatteDelegate{

    @BindView(R.id.et_sign_up_phone)
    TextInputEditText et_sign_up_phone;

    @BindView(R.id.et_sign_up_password)
    TextInputEditText et_sign_up_password;

    @BindView(R.id.bt_login)
    Button bt_login;

    @BindView(R.id.cb_sign_up_keep_password)
    AppCompatCheckBox cb_sign_up_keep_password;

    @BindView(R.id.tv_sign_up_find_password)
    AppCompatTextView tv_sign_up_find_passwrod;

    @BindView(R.id.tv_go_to_register)
    AppCompatTextView tv_go_to_register;

    @OnClick(R.id.bt_login)
    void onClick(){

    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void onBindView() {

    }
}
