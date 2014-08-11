package com.noveogroup.task7.database;

import android.content.Context;

import com.noveogroup.task7.R;

import java.util.HashMap;
import java.util.Map;

public class CarsContentMaker {
    public static Map<String, Integer> getExampleItems(Context context) {
        String[] itemsString = context.getResources().getStringArray(R.array.example_items);
        HashMap<String, Integer> items = new HashMap<String, Integer>();
        for (String item : itemsString) {
            String[] itemSplit = item.split(",");
            items.put(itemSplit[0], Integer.valueOf(itemSplit[1]));
        }

        return items;
    }
}
