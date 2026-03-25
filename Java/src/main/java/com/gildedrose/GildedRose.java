package com.gildedrose;

class GildedRose {

    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);

            if (!"Sulfuras, Hand of Ragnaros".equals(item.name)) {
                decreaseSellIn(item);
            }
        }
    }

    /**
     * Update item quality
     * @param item an item
     */
    private void updateItemQuality(Item item) {
        switch (item.name) {
            case "Aged Brie":
                increaseQuality(item);

                if (item.sellIn <= 0) {
                    increaseQuality(item);
                }

                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                increaseQuality(item);

                if (item.sellIn <= 10) {
                    increaseQuality(item);
                }

                if (item.sellIn <= 5) {
                    increaseQuality(item);
                }

                if (item.sellIn <= 0) {
                    item.quality = MIN_QUALITY;
                }

                break;
            case "Sulfuras, Hand of Ragnaros":
                break;
            case "Conjured":
                decreaseQuality(item);
                decreaseQuality(item);

                break;
            default:
                decreaseQuality(item);

                if (item.sellIn <= 0) {
                    decreaseQuality(item);
                }

                break;
        }
    }

    /**
     * Decrease quality till its min
     * @param item an item
     */
    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - 1;
        }
    }

    /**
     * Decrease item sellIn
     * @param item an item
     */
    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    /**
     * Increase item quality till its max
     * @param item an item
     */
    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }
}
