package com.example.alan.customframe.delegate.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.alan.customframe.recycler.DataConverter;
import com.example.alan.customframe.recycler.MultipleFields;
import com.example.alan.customframe.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Function :
 * Modify Date : 2018/1/29
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class CartDataConverter extends DataConverter{
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final com.alibaba.fastjson.JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String desc = data.getString("desc");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.IS_SELECTED, false)
                    .setField(ShopCartItemFields.POSITION, i)
                    .build();

            dataList.add(entity);
        }

        return dataList;
    }
}
