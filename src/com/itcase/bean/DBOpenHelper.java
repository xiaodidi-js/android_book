package com.itcase.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

public class DBOpenHelper extends SQLiteOpenHelper {

	// 版本号
	private static int version = 1;
	// 数据库名称
	private final static String DATABASENAME = "wechat.db";
	// ID
	private final static int ID = 1;
	// 数据库操作对象
	private SQLiteDatabase db;

	public DBOpenHelper(Context context) {
		super(context, DATABASENAME, null, version);
		// 在子类的构造函数实现过程中.一般首先调用父类的构造函数
		db = getWritableDatabase();
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建表SQL语句
		String FirendsSql = "create table firends (id Integer primary key AUTOINCREMENT,name varchar(50),phone varchar(20),address varchar(50),qq varchar(20))";
		String UserSql = "create table users (id Integer primary key AUTOINCREMENT,username varchar(50),password varchar(20),phone varchar(50))";
		// 执行SQL语句
		db.execSQL(FirendsSql);
		db.execSQL(UserSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}