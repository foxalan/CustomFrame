package com.example.alan.customframe.delegate.sign;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function : 登录页面
 * Modify Date : 2017/12/28
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class LoginDelegate extends LatteDelegate {

    @BindView(R.id.et_sign_up_phone)
    TextInputEditText et_sign_up_phone;
    @BindView(R.id.et_sign_up_password)
    TextInputEditText et_sign_up_password;
    @BindView(R.id.cb_sign_up_keep_password)
    AppCompatCheckBox cb_sign_up_keep_password;
    @BindView(R.id.tv_sign_up_find_password)
    AppCompatTextView tv_sign_up_find_password;

    private int phoneCount = 11;

    @OnClick({R.id.bt_login, R.id.tv_go_to_register})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                if (checkFrom()) {
                    //todo
                }
                break;
            case R.id.tv_go_to_register:
                start(new RegisterDelegate());
                break;
            default:
                break;
        }
    }

    boolean checkFrom() {
        boolean isPass = true;
        String phone = et_sign_up_phone.getText().toString();
        String password = et_sign_up_password.getText().toString();

        if (phone.isEmpty() || phone.length() != phoneCount ) {
            isPass = false;
            et_sign_up_phone.setError("请输入正确的电话号码");
        } else {
            et_sign_up_phone.setError(null);
        }

        if (password.isEmpty()) {
            isPass = false;
            et_sign_up_password.setError("请输入密码");
        } else {
            et_sign_up_password.setError(null);
        }

        return isPass;
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void onBindView() {

    }
}
