package com.example.alan.customframe.delegate.launcher;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.sign.LoginDelegate;
import com.example.alan.customframe.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function : 广告页面
 * Modify Date : 2017/12/27
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class AdvertisementDelegate extends LatteDelegate implements OnItemClickListener {

    @BindView(R.id.convenientBanner)
    ConvenientBanner<Integer> convenientBanner;

    private int[] images = new int[]{R.mipmap.ad_01, R.mipmap.ad_02, R.mipmap.ad_03};
    private List<Integer> integers = new ArrayList<>();

    private void initBanner() {

        initImageData();
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, integers)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator_normal, R.drawable.ic_page_indicator_pressed})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this);

    }

    //初始化数据
    private void initImageData() {
        for (int id : images) {
            integers.add(id);
        }
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_advertisement;
    }

    @Override
    public void onBindView() {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        LogUtil.e(position + "====");
        if (position == (images.length - 1)) {

            start(new LoginDelegate());
        }
    }
}
