package com.itcase.bean;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {

	DBOpenHelper dbOpenHelper;
	
	@Override
	public boolean onCreate() {
		dbOpenHelper = new DBOpenHelper(this.getContext());
		return true;
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		return 0;
	}
	
	//返回你操作的类型文件
	@Override
	public String getType(Uri uri) {
		return null;
	}

	
	@Override
	public Uri insert(Uri uri, ContentValues value) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		return null;
	}

	

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues value, String selection, String[] selectionArgs) {
		return 0;
	}

}
