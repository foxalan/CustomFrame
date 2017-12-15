package com.example.alan.customframe.loading;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;


@SuppressWarnings("unused")
public class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    public static AVLoadingIndicatorView loadingIndicatorView(String type, Context context) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if (LOADING_MAP.get(type) == null) {
            Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);

        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));

        return avLoadingIndicatorView;
    }

    /**
     * 默认的Loading
     * @param context
     * @return
     */
    private AVLoadingIndicatorView loadingIndicatorView(Context context) {
        return loadingIndicatorView(LoadingIndicator.BallBeatIndicator.name(), context);
    }

    /**
     * 利用反射机制获取Indicator
     *
     * @param name
     * @return
     */
    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        final StringBuffer drawableClassName = new StringBuffer();
        if (!name.contains(".")) {
            String packageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(packageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);

        try {
            Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;

    }


}
