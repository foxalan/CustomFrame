package com.example.alan.customframe.delegate;

import android.util.Log;

import com.example.alan.customframe.R;


/**
 * @author Alan
 */

public class TestDelegate extends LatteDelegate {

    private static TestDelegate testDelegate;
    private static final String TAG = "TestDelegate";

    public static TestDelegate getInstance() {
        if (testDelegate == null) {
            testDelegate = new TestDelegate();
        }

        return testDelegate;
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_test;
    }

    @Override
    public void onBindView() {
        Log.e(TAG, "onBindView: ");

//        RestClient.builder().
//                setError(new IError() {
//                    @Override
//                    public void onError(int code, String message) {
//                        Log.e(TAG, "onError: "+ message);
//                    }
//                }).setUrl("https://tieba.baidu.com")
//                .setSuccess(new ISuccess() {
//                    @Override
//                    public void onSuccess(String message) {
//                        Log.e(TAG, "onSuccess: "+ message);
//                    }
//                })
//                .setError(new IError() {
//                    @Override
//                    public void onError(int code, String message) {
//                        Log.e(TAG, "onError: "+message );
//                    }
//                })
//                .setFailure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//
//                    }
//                })
//                .loader(getContext(), LoadingIndicator.PacmanIndicator)
//                .build()
//                .get();
    }


}
