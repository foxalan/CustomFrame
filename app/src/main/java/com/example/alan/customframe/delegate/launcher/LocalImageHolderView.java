package com.example.alan.customframe.delegate.launcher;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Function :
 * Modify Date : 2017/12/27
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class LocalImageHolderView implements Holder<Integer>{
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, Integer data) {
        imageView.setImageResource(data);
    }

}
