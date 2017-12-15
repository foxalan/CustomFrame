package com.example.alan.customframe.loading;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.alan.customframe.R;
import com.example.latten_corn.util.DimenUtil;

import java.util.ArrayList;


public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAULT_TYPE = LoadingIndicator.BallBeatIndicator.name();

    public static void showLoading(Context context,String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.loading_dialog);
        dialog.setContentView(LoaderCreator.loadingIndicatorView(type,context));

        int screen_width = DimenUtil.getScreenWidth();
        int screen_height = DimenUtil.getScreenHeight();

        Window window = dialog.getWindow();

        if (window!=null){

            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.width = screen_width/LOADER_SIZE_SCALE;
            layoutParams.height = screen_height/LOADER_SIZE_SCALE;
        }

        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_TYPE);
    }



    public static void stopLoading(){
        for (AppCompatDialog dialog:LOADERS){
            if (dialog != null){
                if (dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
