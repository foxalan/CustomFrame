package com.example.alan.customframe.camera.camera;

import android.net.Uri;

import com.example.alan.customframe.delegate.PermissionCheckerDelegate;
import com.example.alan.customframe.util.FileUtil;


/**
 * Created by 傅令杰
 * 照相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
