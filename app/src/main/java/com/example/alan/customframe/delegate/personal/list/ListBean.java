package com.example.alan.customframe.delegate.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.alan.customframe.delegate.LatteDelegate;

/**
 * Function :
 * Modify Date : 2018/1/30
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ListBean implements MultiItemEntity {

    private int mId;
    private String mContent;
    private String mBranch;
    private String mUrl;
    private int mItemType;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListBean(int mId, String mContent, String mBranch, String mUrl,
                    int mItemType, LatteDelegate mDelegate,
                    CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mId = mId;
        this.mContent = mContent;
        this.mBranch = mBranch;
        this.mUrl = mUrl;
        this.mItemType = mItemType;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public int getId() {
        return mId;
    }

    public String getContent() {
        return mContent;
    }

    public String getBranch() {
        return mBranch;
    }

    public String getUrl() {
        return mUrl;
    }



    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }


    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public static class Builder {

        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;
        private LatteDelegate delegate = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public ListBean build() {
            return new ListBean(id, text, value, imageUrl, itemType, delegate,onCheckedChangeListener);
        }

    }
}
