package com.example.alan.customframe.delegate.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ContentFrameLayout;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.alan.customframe.R;
import com.example.alan.customframe.banner.HolderCreator;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.net.RestClient;
import com.example.alan.customframe.net.callback.ISuccess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Function :
 * Modify Date : 2018/1/31
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class GoodsDetailDelegate extends LatteDelegate {

    @BindView(R.id.detail_banner)
    ConvenientBanner<String> mConvenientBanner;
    @BindView(R.id.frame_goods_info)
    ContentFrameLayout mFrameLayout;
    @BindView(R.id.tab_layout)
    TabLayout mTableLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_good_detail;
    }

    @Override
    public void onBindView() {

        initData();

    }

    private void initData() {

        RestClient.builder()
                .url("goods_detail.php")
                .params("goods_id", mGoodsId)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject data =
                                JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
        //                setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }

    /**
     * 初始化顶部广告数据
     * @param data
     */
    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }
        mConvenientBanner
                .setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().
                loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }






}
