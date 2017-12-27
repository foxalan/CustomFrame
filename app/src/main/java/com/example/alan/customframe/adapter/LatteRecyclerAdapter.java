package com.example.alan.customframe.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Function : 建立根RecyclerAdapter
 * <p>
 * Modify Date : 2017/12/20
 * Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class LatteRecyclerAdapter<T> extends RecyclerView.Adapter<LatteRecyclerAdapter.LatteViewHolder> {

    private List<T> mData;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LatteRecyclerAdapter(List<T> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(final LatteRecyclerAdapter.LatteViewHolder holder, final int position) {

        holder.getParentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, holder);
            }
        });
    }


    public interface OnItemClickListener {
        void onItemClick(int position, LatteViewHolder holder);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class LatteViewHolder extends RecyclerView.ViewHolder {

        private View parentView;

        public View getParentView() {
            return parentView;
        }

        public void setParentView(View parentView) {
            this.parentView = parentView;
        }

        public LatteViewHolder(View itemView) {
            super(itemView);
            setParentView(itemView);
        }

        public <T extends View> T getChildView(int resId) {

            return parentView.findViewById(resId);
        }


    }
}
