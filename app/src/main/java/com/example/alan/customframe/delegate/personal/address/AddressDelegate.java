package com.example.alan.customframe.delegate.personal.address;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.net.RestClient;
import com.example.alan.customframe.net.callback.ISuccess;
import com.example.alan.customframe.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 傅令杰
 */

public class AddressDelegate extends LatteDelegate implements ISuccess {

    @BindView(R.id.rv_address)
    RecyclerView mRecyclerView = null;



    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView() {
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }
}
