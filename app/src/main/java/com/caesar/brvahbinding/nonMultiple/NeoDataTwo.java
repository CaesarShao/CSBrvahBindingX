package com.caesar.brvahbinding.nonMultiple;

public class NeoDataTwo extends customData {
    public NeoDataTwo(String title, String discribe, int imgRes) {
        super(title, discribe, imgRes);
    }

    @Override
    public int getItemType() {
        return 2;
    }
}
