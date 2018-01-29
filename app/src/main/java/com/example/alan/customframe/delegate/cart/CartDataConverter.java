package com.example.alan.customframe.delegate.cart;

import com.alibaba.fastjson.JSON;
import com.example.alan.customframe.recycler.DataConverter;
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
        final int size = dataArray.size();



        return null;
    }
}
