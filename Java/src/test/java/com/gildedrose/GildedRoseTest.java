package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void agedBrieQualityGetsBetterWhenGettingOld() {
        Item[] items = new Item[] { new Item("Aged Brie", 4, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(6, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void agedBrieQualityGetsEvenBetterWhenSellInIsExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(7, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void legendaryItemNeverChanges() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 4, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(5, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void qualityCannotBeNegative() {
        Item[] items = new Item[] { new Item("Another item", 4, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void backstagePassesQualityDropsWhenSellInExpired() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void backstagePassesQualityGetsEvenBetterWhenSellInIsClosedToExpire() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 4, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(43, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }

    @Test
    void conjuredItemQualityDropsFaster() {
        Item[] items = new Item[] { new Item("Conjured", 4, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(38, app.items[0].quality);
        assertEquals(3, app.items[0].sellIn);
    }
}
