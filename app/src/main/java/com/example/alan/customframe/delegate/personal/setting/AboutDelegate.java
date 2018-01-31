package com.example.alan.customframe.delegate.personal.setting;

import android.support.v7.widget.AppCompatTextView;

import com.alibaba.fastjson.JSON;
import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.net.RestClient;
import com.example.alan.customframe.net.callback.ISuccess;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/31
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class AboutDelegate extends LatteDelegate {

    @BindView(R.id.tv_info)
    AppCompatTextView mTextView;


    @Override
    public Object getLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBindView() {
        RestClient.builder()
                .url("about.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        mTextView.setText(info);
                        mTextView.append("");
                    }
                })
                .build()
                .get();
    }
}
