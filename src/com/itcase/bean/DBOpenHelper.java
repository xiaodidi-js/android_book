package com.itcase.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

public class DBOpenHelper extends SQLiteOpenHelper {

	// �汾��
	private static int version = 1;
	// ���ݿ�����
	private final static String DATABASENAME = "wechat.db";
	// ID
	private final static int ID = 1;
	// ���ݿ��������
	private SQLiteDatabase db;

	public DBOpenHelper(Context context) {
		super(context, DATABASENAME, null, version);
		// ������Ĺ��캯��ʵ�ֹ�����.һ�����ȵ��ø���Ĺ��캯��
		db = getWritableDatabase();
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// ������SQL���
		String FirendsSql = "create table firends (id Integer primary key AUTOINCREMENT,name varchar(50),phone varchar(20),address varchar(50),qq varchar(20))";
		String UserSql = "create table users (id Integer primary key AUTOINCREMENT,username varchar(50),password varchar(20),phone varchar(50))";
		// ִ��SQL���
		db.execSQL(FirendsSql);
		db.execSQL(UserSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}