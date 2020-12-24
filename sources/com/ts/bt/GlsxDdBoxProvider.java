package com.ts.bt;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;

public class GlsxDdBoxProvider extends ContentProvider {
    public static final String AUTHORITY = "com.glsx.bt.provider";
    public static Uri AUTHORITY_URI = Uri.parse("content://com.glsx.bt.provider/phonebook");
    public static final int PHONEBOOK_DIR = 0;
    public static final int PHONEBOOK_ITEM = 1;
    private static UriMatcher mUriMatcher = new UriMatcher(-1);
    private PhoneBookDatabaseHelper dbHelper;
    private boolean mIsInTransaction = false;

    static {
        mUriMatcher.addURI(AUTHORITY, "phonebook", 0);
        mUriMatcher.addURI(AUTHORITY, "phonebook/#", 1);
    }

    public boolean onCreate() {
        this.dbHelper = new PhoneBookDatabaseHelper(getContext(), "PhoneBook.db", (SQLiteDatabase.CursorFactory) null, 1);
        return true;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case 0:
                return db.delete("phonebook", (String) null, (String[]) null);
            case 1:
                return db.delete("phonebook", "id = ?", new String[]{uri.getPathSegments().get(1)});
            default:
                return 0;
        }
    }

    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case 0:
                return "vnd.android.cursor.dir/vnd.com.glsx.bt.provider.phonebook";
            case 1:
                return "vnd.android.cursor.item/vnd.com.glsx.bt.provider.phonebook";
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        switch (mUriMatcher.match(uri)) {
            case 0:
            case 1:
                return Uri.parse("content://com.glsx.bt.provider/phonebook/" + db.insert("phonebook", (String) null, values));
            default:
                return null;
        }
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        switch (mUriMatcher.match(uri)) {
            case 0:
                return db.query("phonebook", projection, selection, selectionArgs, (String) null, (String) null, sortOrder);
            case 1:
                return db.query("phonebook", projection, "id = ?", new String[]{uri.getPathSegments().get(1)}, (String) null, (String) null, sortOrder);
            default:
                return null;
        }
    }

    public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
        return 0;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        if (this.dbHelper == null) {
            Log.d("lh", "dbHelper is null");
            return null;
        }
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        db.beginTransaction();
        this.mIsInTransaction = true;
        try {
            ContentProviderResult[] applyBatch = super.applyBatch(operations);
            db.setTransactionSuccessful();
            return applyBatch;
        } finally {
            this.mIsInTransaction = false;
            db.endTransaction();
            notifyChange();
        }
    }

    /* access modifiers changed from: protected */
    public void notifyChange() {
        if (!this.mIsInTransaction) {
            getContext().getContentResolver().notifyChange(AUTHORITY_URI, (ContentObserver) null, false);
        }
    }
}
