package com.example.alan.customframe.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;



abstract class BaseDelegate extends SwipeBackFragment {

    public abstract Object getLayout();

    public abstract void onBindView();

    public Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;

        if (getLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) getLayout(), container, false);
        } else if (getLayout() instanceof View) {
            rootView = (View) getLayout();
        }

        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
            onBindView();
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null){
            unbinder.unbind();
        }
    }
}
