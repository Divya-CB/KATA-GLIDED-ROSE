package com.glidedRoseKATA;

import java.util.Arrays;
import java.util.Objects;

public class GildedRoseKATA {

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured Mana Cake";
    private static final int AGED_BRIE_ITEM_QUALITY_LIMIT = 50;
    private static final int CONJURED_DEGRADE_AMOUNT = 2;
    private static final int DEFAULT_DEGRADE_AMOUNT = 1;

    Item[] items;

    public GildedRoseKATA(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            switch (item.name) {
                case SULFURAS_HAND_OF_RAGNAROS:
                    break;
                case AGED_BRIE:
                    if (item.quality < AGED_BRIE_ITEM_QUALITY_LIMIT)  item.quality++;
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT:
                    getBackstageQuality(item);
                    break;
                case CONJURED:
                    item.quality -= checkSellIn(CONJURED, item.sellIn, CONJURED_DEGRADE_AMOUNT);
                    break;
                default:
                    item.quality -= checkSellIn(null, item.sellIn, DEFAULT_DEGRADE_AMOUNT);
                    break;
            }

            if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                item.quality = adjustQuality(item.quality);
                item.sellIn--;
            }
        });
    }

    private int adjustQuality(int quality) {
        return Math.max(0, Math.min(50, quality));
    }

    private static void getBackstageQuality(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else {
            int qualityIncrease = 1;
            if (item.sellIn <= 5) {
                qualityIncrease = 3;
            } else if (item.sellIn <= 10) {
                qualityIncrease = 2;
            }
            item.quality += qualityIncrease;
        }
    }

    private static int checkSellIn(String type, int sellIn, int degradeAmount){
        if(sellIn <= 0) {
            return degradeAmount * 2;
        } else return degradeAmount;
    }
}
