package com.example.vocabularybook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class MyContentProvider extends ContentProvider
{
    private final int DIR = 0;
    private final int ITEM = 1;
    private final String AUTHORITY = "com.example.vocabularybook.provider";

    private UriMatcher uriMatcher;

    public MyContentProvider()
    {
        this.uriMatcher = new UriMatcher((UriMatcher.NO_MATCH));
        uriMatcher.addURI(AUTHORITY, "Database", DIR);
        uriMatcher.addURI(AUTHORITY, "Database/#", ITEM);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(getContext(), "VocabularyBook.db", null, 1);
        SQLiteDatabase database = openHelper.getReadableDatabase();
        int matachRes = uriMatcher.match(uri);
        Cursor cursor = null;
        if(matachRes == DIR)
        {
            final String SQL = "SELECT * FROM 'Database'";
            Log.d("MyContentProvider", "executing SQL for content provider: " + SQL);
            cursor = database.rawQuery(SQL, null);
        }
        else if(matachRes == ITEM)
        {
            final String SQL = "SELECT * FROM 'Database' WHERE ENG = ?";
            Log.d("MyContentProvider", "executing SQL for content provider: " + SQL);
            cursor = database.rawQuery(SQL, selectionArgs);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
