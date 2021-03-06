package com.example.tmdbandroid.services.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.tmdbandroid.DTOs.Item;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class LocalStorageConnector {
    private static final String watchListKey = "watchlist";

    SharedPreferences pref;
    Gson gson;

    public LocalStorageConnector(Context context) {
        pref =  context.getSharedPreferences("TMDB", Context.MODE_PRIVATE);  // getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        gson = new Gson();
    }

    public void saveWatchList(List<Item> watchLists) {
        SharedPreferences.Editor editor = pref.edit();
        String jsonTxtData = gson.toJson(watchLists);
        editor.putString(watchListKey, jsonTxtData);
        editor.apply();
    }

    public void addToWatchList(Item newItem) {
        ArrayList<Item> watchList = new ArrayList<>(getWatchList());
        int currentIndex = -1;
        for (int i =0; i<watchList.size(); i++){
            if (newItem.id.equals(watchList.get(i).id)){
                currentIndex = i;
                break;
            }
        }
        if (currentIndex >= 0 && currentIndex < watchList.size()){
            watchList.remove(currentIndex);
        }
        watchList.add(0, newItem);

        saveWatchList(watchList);
    }

    public List<Item> getWatchList() {
        String jsonTxtData = pref.getString(watchListKey,"[]");
        List<Item> watchList = Arrays.asList(gson.fromJson(jsonTxtData, Item[].class));
        return watchList;
    }

    public boolean isItemInWatchList(Item newItem) {
        ArrayList<Item> watchList = new ArrayList<>(getWatchList());
        for (int i =0; i<watchList.size(); i++){
            if (newItem.id.equals(watchList.get(i).id)){
                return true;
            }
        }
        return false;
    }

    public void removeFromWatchList(String itemId, String itemCategory) {
        ArrayList<Item> watchList = new ArrayList<>(getWatchList());
        int currentIndex = -1;
        for (int i =0; i<watchList.size(); i++){
            if (itemId.equals(watchList.get(i).id) && itemCategory.equals((watchList.get(i).category))){
                currentIndex = i;
                break;
            }
        }
        if (currentIndex >= 0 && currentIndex < watchList.size()){
            watchList.remove(currentIndex);
        }
        saveWatchList(watchList);
        Log.v("DEVV", "Removed " + watchList.size());
    }
}
