package com.noveogroup.internship.task7.provider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public final class ContentContract {

    private static final String AUTHORITY = "com.noveogroup.internship.task7.provider";
    private static final Uri CONTENT_BASE_URI =
            new Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT).authority(AUTHORITY).build();

    private ContentContract() {
        throw new UnsupportedOperationException();
    }

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) {
        {
            addURI(AUTHORITY, Toys.TABLE_NAME, Toys.ALL_URI_CODE);
            addURI(AUTHORITY, Toys.TABLE_NAME + "/#/", Toys.ITEM_URI_CODE);
        }

        @Override
        public int match(Uri uri) {
            final int result = super.match(uri);
            if (result < 0) {
                throw new IllegalArgumentException("URI " + uri.toString() + " could not be matched.");
            } else {
                return result;
            }
        }

    };

    public static class Toys {
        public static final String TABLE_NAME = "toys";

        public static final Uri TABLE_URI = CONTENT_BASE_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static final int ALL_URI_CODE = 0;
        public static final int ITEM_URI_CODE = 1;

        public static class Cols implements BaseColumns {
            public static final String TITLE = "name";
            public static final String COST = "cost";
        }
    }

    public static String getTableName(int uriCode) {
        switch (uriCode) {
            case Toys.ALL_URI_CODE:
            case Toys.ITEM_URI_CODE:
                return Toys.TABLE_NAME;
        }
        throw new IllegalArgumentException("uriCode " + uriCode);
    }
}
