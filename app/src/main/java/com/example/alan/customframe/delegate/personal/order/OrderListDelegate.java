package com.example.alan.customframe.delegate.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.delegate.personal.PersonalDelegate;
import com.example.alan.customframe.net.RestClient;
import com.example.alan.customframe.net.callback.ISuccess;
import com.example.alan.customframe.recycler.MultipleItemEntity;

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

public class OrderListDelegate extends LatteDelegate {

    @BindView(R.id.rv_order_list)
    RecyclerView mRecyclerView;

    private String mType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.php")
                .params("type", mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}
