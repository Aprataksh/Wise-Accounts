package com.wiselap.accounts.utils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;

public class RealPathUtil {

    private static final String TAG = RealPathUtil.class.getSimpleName();

    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API19(Context context, Uri uri) {

        String filePath = "";
        try {
            String wholeID = DocumentsContract.getDocumentId(uri);

            // Split at colon, use second item in the array
            Log.d(TAG, "PALASH CHECKING WHOLE ID " + wholeID);
            String[] ids = wholeID.split(":");
            String id = "";
            if (ids.length == 2) {
                id = wholeID.split(":")[1];
            } else {
                id = wholeID.split(":")[0];
            }
            String[] column = {MediaStore.Images.Media.DATA};
            Log.d(TAG, "PALASH CHECKING id ID " + id);

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            Log.d(TAG, "FILE PATH " + filePath);
            cursor.close();
        } catch (java.lang.IllegalArgumentException e) {
            e.printStackTrace();
           /* String path = uri.getPath();
             if (path.contains("external_files")){
                path = path.replace("external_files","storage/emulated/0");
            }
            filePath = path;*/
            return getRealPathFromURI_API11to18(context, uri);
        }
        return filePath;
    }


    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;

        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        if (cursor != null) {
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index
                = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}

