package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

public class DefaultQualityBehavior implements QualityBehavior {

    public static final int MAX_QUALITY_LEVEL = 50;
    public static final int MIN_QUALITY_LEVEL = 0;

    @Override
    public void processQualityUpdate(Item item) {
        decreaseQuality(item);
    }

    private void decreaseQuality(Item item) {
        item.quality = limitQuality(item.quality - 1);
    }

    private int limitQuality(int newQuality) {
        return Math.max(MIN_QUALITY_LEVEL, Math.min(MAX_QUALITY_LEVEL, newQuality));
    }
}
