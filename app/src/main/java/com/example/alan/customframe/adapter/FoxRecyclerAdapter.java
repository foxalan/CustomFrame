package com.example.alan.customframe.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Function :
 * Modify Date : 2017/12/20
 * Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class FoxRecyclerAdapter extends LatteRecyclerAdapter<String> {

    public FoxRecyclerAdapter(List<String> mData, Context context) {
        super(mData, context);
    }

    @Override
    public LatteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {




        return null;
    }

    @Override
    public void onBindViewHolder(LatteViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        FoxViewHolder foxViewHolder = (FoxViewHolder) holder;



    }

    class FoxViewHolder extends LatteViewHolder {

        ImageView iv_fox;
        TextView tv_fox;

        public FoxViewHolder(View itemView) {
            super(itemView);

        }



    }


}
