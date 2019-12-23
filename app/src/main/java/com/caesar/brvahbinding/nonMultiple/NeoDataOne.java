package com.caesar.brvahbinding.nonMultiple;

public class NeoDataOne extends customData {
    public NeoDataOne(String title, String discribe, int imgRes) {
        super(title, discribe, imgRes);
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
