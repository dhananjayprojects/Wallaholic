package com.imagineappdev.wallaholic;

import android.provider.BaseColumns;

/**
 * Created by Anonymous on 03-05-2017.
 */

final class FavouriteContract {

    private FavouriteContract(){}

    static class FavouriteEntry implements BaseColumns {
        static final String TABLE_NAME = "favouriteTable";
        static final String COLUMN_IMAGENAME = "imageName";
    }

}
