package com.example.alan.customframe.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.alan.customframe.R;
import com.example.alan.customframe.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/24
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>  implements
        OnItemClickListener, BaseQuickAdapter.SpanSizeLookup {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    /**
     * 设置不同的布局
     */
    private void init() {

        addItemType(ItemType.TYPE_TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.TYPE_IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TYPE_TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.TYPE_SCANNER, R.layout.item_multiple_banner);

        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    /**
     * 设置图片加载策略
     */
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    private boolean mIsInitBanner = false;

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        String text;
        String imageUrl;
        ArrayList<String> bannerImages;
        switch (helper.getItemViewType()) {
            case ItemType.TYPE_TEXT:
                text = item.getField(MultipleFields.TEXT);
                helper.setText(R.id.text_single, text);
                break;
            case ItemType.TYPE_IMAGE:
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_single));
                break;
            case ItemType.TYPE_TEXT_IMAGE:
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                text = item.getField(MultipleFields.TEXT);
                helper.setText(R.id.tv_multiple, text);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.tv_multiple));
                break;
            case ItemType.TYPE_SCANNER:
                /**
                 * 设置只初始化一次
                 */
                if (!mIsInitBanner) {
                    bannerImages = item.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = helper.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }

            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
