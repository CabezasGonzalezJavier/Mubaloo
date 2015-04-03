package com.thedeveloperworldisyours.mubalooorganisation.dummy;

import com.thedeveloperworldisyours.mubalooorganisation.interfaces.Item;
import com.thedeveloperworldisyours.mubalooorganisation.models.DummyItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static void insertProfile(DummyItem profile) {

        addItem(profile); // the same addItem provided with the eclipse wizard
    }


    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public static void removeItem(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }

}