package com.noveogroup.task7.database;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public final class CarsContract {

    private static final String AUTHORITY = "com.noveogroup.task7.database.CarsContentProvider";
    private static final Uri BASE_URI = new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
            .authority(AUTHORITY).build();

    public static final String QUERY_PARAMETER_LIMIT = "limit";

    private CarsContract() {
        throw new UnsupportedOperationException("CarsContract class instance can not be created.");
    }

    public static class Cars {
        public static final String TABLE_NAME = "cars";
        public static final Uri TABLE_URI = BASE_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final int ALL_URI_CODE = 0;
        public static final int SINGLE_URI_CODE = 1;

        public static class Columns {
            public static final String ID = BaseColumns._ID;
            public static final String MODEL = "model";
            public static final String PRICE = "price";
        }

    }

    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) {
        {
            addURI(AUTHORITY, Cars.TABLE_NAME, Cars.ALL_URI_CODE);
            addURI(AUTHORITY, Cars.TABLE_NAME + "/#/", Cars.SINGLE_URI_CODE);
        }
    };

    public static String getTableName(Uri uri) {
        int uriCode = uriMatcher.match(uri);
        switch (uriCode) {
            case Cars.ALL_URI_CODE:
            case Cars.SINGLE_URI_CODE:
                return Cars.TABLE_NAME;
        }
        return null;
    }
}
