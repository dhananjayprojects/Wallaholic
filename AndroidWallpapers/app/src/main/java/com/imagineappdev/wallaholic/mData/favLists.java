package com.imagineappdev.wallaholic.mData;

import android.content.Context;
import android.database.Cursor;

import com.imagineappdev.wallaholic.FavouriteActivity;
import com.imagineappdev.wallaholic.FavouriteDbHelper;

import java.util.ArrayList;

public class favLists {

    public static ArrayList<ImagesGetSet> imagesGetSets() {
        ArrayList<ImagesGetSet> images = new ArrayList<>();
        ImagesGetSet img;

        Context applicationContext = FavouriteActivity.getContextOfApplication();

        FavouriteDbHelper mFavouriteDbHelper;
        mFavouriteDbHelper = new FavouriteDbHelper(applicationContext);
        Cursor data1 = mFavouriteDbHelper.getImageName();
        ArrayList<String> imageName = new ArrayList<>();

        while (data1.moveToNext()){
            imageName.add(data1.getString(0));
        }

        for (String s : imageName)
        {
            img = new ImagesGetSet();
            img.setUrl(s);
            images.add(img);
        }

        /*for () {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());

            img = new ImagesGetSet();
            img.setUrl(entry.getKey() + ": " + entry.getValue().toString());
            images.add(img);
        }*/

        return images;
    }

}


