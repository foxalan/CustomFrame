package com.example.alan.customframe.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by 傅令杰
 */

public class HolderCreator  implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}