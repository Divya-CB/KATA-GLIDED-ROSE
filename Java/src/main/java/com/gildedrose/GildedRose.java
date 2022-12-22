package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    final List<Item> items;

    public GildedRose(Item[] items) {
        this.items = Arrays.asList(items);
    }

    public void updateQuality() {
        items.forEach(GildedRose::handleDay);
    }

    private static void handleDay(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                handleAgedBrie(item);
                return;
            case SULFURAS:
                handleSulfuras(item);
                return;
            case BACKSTAGE_PASSES:
                handleBackstagePasses(item);
                return;
        }
        if (!item.name.equals(AGED_BRIE)
            && !item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS)) {
                    item.quality = item.quality - 1;
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(SULFURAS)) {
                            item.quality = item.quality - 1;
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

    private static void handleBackstagePasses(Item item) {
        // todo: make it so these items can't be initialized with over 50
        if (item.quality == 50) {
            return;
        }
        if (item.sellIn == 0) {
            item.quality = 0;
        } else if (item.sellIn <= 5) {
            item.quality += 3;
        } else if (item.sellIn <= 10) {
            item.quality += 2;
        }
        item.sellIn -= 1;
    }

    private static void handleSulfuras(Item item) {

    }

    private static void handleAgedBrie(Item item) {
        if (item.quality != 50) {
            item.quality += 1;
        }
        item.sellIn -= 1;
    }
}
