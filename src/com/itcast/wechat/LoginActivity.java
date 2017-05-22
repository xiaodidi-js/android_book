package com.itcast.wechat;

import com.itcase.bean.DBOpenHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class LoginActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.username)
	private EditText author;
	@ViewInject(R.id.password)
	private EditText number;
	@ViewInject(R.id.LoginConfig)
	private Button login;
	@ViewInject(R.id.RegConfig)
	private TextView reg;
	private Cursor cursor = null;
	private DBOpenHelper db;
	private SQLiteDatabase database;

	private static final String PREFS_NAME = "保存用户信息";
	private String saveOfUserName;
	private SharedPreferences Readershared, SaveShared;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		ViewUtils.inject(this);
		login.setOnClickListener(this);
		reg.setOnClickListener(this);
		db = new DBOpenHelper(this);
		// 读取用户名信息是否存在
		read();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.LoginConfig:
			String username = author.getText().toString();
			String passwords = number.getText().toString();
			// sql查询语句
			String sql = "select * from users where username = ? and password = ?";
			cursor = db.getReadableDatabase().rawQuery(sql,
					new String[] { username, passwords });
			if (username.equals("") && passwords.equals("")) {
				ShowToast("请输入用户名或者密码~~~");
			} else if (cursor.moveToFirst()) {
				if (username.equals(cursor.getString(cursor
						.getColumnIndex("username")))
						&& passwords.equals(cursor.getString(cursor
								.getColumnIndex("password")))) {
					ShowToast("登录成功!");
					// 保存用户信息
					SaveUserInfo();
					// 显式跳转
					Intent intent = new Intent(new Intent(LoginActivity.this,
							MeActivity.class));
					startActivity(intent);
					finish();
				} else {
					ShowToast("登录失败!");
				}
			} else {
				ShowToast("登录失败!请注册");
			}
			// 关闭数据库
			db.close();
			break;
		case R.id.RegConfig:
			startActivity(new Intent(LoginActivity.this, RegActivity.class));
			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

	/**
	 * 读取用户信息
	 */
	public void read() {
		Readershared = getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_READABLE);
		saveOfUserName = Readershared.getString("author", saveOfUserName);
		if (saveOfUserName != null && saveOfUserName != "") {
			author.setText(saveOfUserName);
			startActivity(new Intent(LoginActivity.this, MeActivity.class));
			finish();
		}
	}

	/**
	 * 保存用户信息
	 */
	private void SaveUserInfo() {
		SaveShared = getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_READABLE);
		SharedPreferences.Editor editor = SaveShared.edit();
		saveOfUserName = author.getText().toString();
		if (saveOfUserName != null) {
			editor.putString("author", saveOfUserName);
		}
		editor.commit();
	}

	private void ShowToast(String str) {
		Toast show = Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG);
		// 设置Toast 显示的位置
		// show.setGravity(Gravity.TOP, 0, 200);
		show.show();
	}
}
