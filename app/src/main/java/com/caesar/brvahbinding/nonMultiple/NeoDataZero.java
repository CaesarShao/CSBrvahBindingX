package com.caesar.brvahbinding.nonMultiple;

public class NeoDataZero extends customData {
    public NeoDataZero(String title, String discribe, int imgRes) {
        super(title, discribe, imgRes);
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
