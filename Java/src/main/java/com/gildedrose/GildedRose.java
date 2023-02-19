package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            // first if defnie what should decrease and what should increase
            if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert") |
                items[i].name.equals("Aged Brie")) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11 && items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1;
                        }
                        if (items[i].sellIn < 6 && items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1;
                        }
                    }
                }
            } else {
                if (items[i].quality > 0 & !items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                    items[i].quality = items[i].quality - 1;

                    if (items[i].name.equals("Conjured Mana Cake") & items[i].quality > 0) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) { // continue
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (items[i].name.equals("Aged Brie")) {

                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }

                }
                if (items[i].name.equals("+5 Dexterity Vest") | items[i].name.equals("Elixir of the Mongoose") |
                    items[i].name.equals("Conjured Mana Cake")) {
                    updateItem(items[i]);
                }
                if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    items[i].quality = items[i].quality - items[i].quality;
                }


            }
        }
    }

    public void updateItem(Item item) {
        if (item.quality > 0 & !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;

            if (item.name.equals("Conjured Mana Cake") && item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }

    }

}
