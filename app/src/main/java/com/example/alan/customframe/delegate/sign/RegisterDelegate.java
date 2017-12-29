package com.example.alan.customframe.delegate.sign;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function : 注册
 * Modify Date : 2017/12/28
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class RegisterDelegate extends LatteDelegate {
    @BindView(R.id.et_register_phone)
    TextInputEditText et_register_phone;
    @BindView(R.id.et_register_password)
    TextInputEditText et_register_password;
    @BindView(R.id.et_register_password_again)
    TextInputEditText et_register_password_again;
    @BindView(R.id.et_register_prove)
    TextInputEditText et_register_prove;

    private int phoneCount = 11;

    @OnClick({R.id.bt_register, R.id.tv_go_to_sign})
    void onClickRegister(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                if (checkForm()) {

                }
                break;
            case R.id.tv_go_to_sign:
                start(new LoginDelegate(),SINGLETASK);
                break;
            default:
                break;
        }

    }

    boolean checkForm() {
        boolean isPass = true;
        String phone = et_register_phone.getText().toString();
        String password = et_register_password.getText().toString();
        String rePassword = et_register_password_again.getText().toString();
        String prove = et_register_prove.getText().toString();
        if (phone.isEmpty() || phone.length() != phoneCount) {
            et_register_phone.setError("请输入正确的电话号码");
            isPass = false;
        } else {
            et_register_phone.setError(null);
        }

        if (password.isEmpty()) {
            et_register_password.setError("请输入密码");
            isPass = false;
        } else {
            et_register_password.setError(null);
        }

        if (rePassword.isEmpty()) {
            et_register_password_again.setError("请再次输入密码");
            isPass = false;
        } else {
            et_register_password_again.setError(null);
        }

        if (!password.isEmpty() && !rePassword.equals(password)) {
            et_register_password_again.setError("两次密码不一样");
            isPass = false;
        } else {
            et_register_password_again.setError(null);
        }

        if (prove.isEmpty()) {
            et_register_prove.setError("请输入验证码");
            isPass = false;
        } else {
            et_register_prove.setError(null);
        }

        return isPass;

    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_register;
    }

    @Override
    public void onBindView() {

    }
}
