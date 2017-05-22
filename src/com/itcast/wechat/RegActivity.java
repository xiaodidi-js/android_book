package com.itcast.wechat;

import com.itcase.bean.DBOpenHelper;
import com.itcase.bean.OptionTable;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.NewuserText)
	private EditText user;
	@ViewInject(R.id.NewpwdText)
	private EditText pwd;
	@ViewInject(R.id.NewphoneText)
	private EditText phone;
	@ViewInject(R.id.loginback)
	private TextView loginback;
	@ViewInject(R.id.RegisterConfig)
	private Button RegisterConfig;
	private DBOpenHelper db;
	private Cursor result;
	private OptionTable option;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newuser);
		ViewUtils.inject(this);
		RegisterConfig.setOnClickListener(this);
		loginback.setOnClickListener(this);
		db = new DBOpenHelper(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RegisterConfig:
			String username = user.getText().toString().trim();
			String password = pwd.getText().toString().trim();
			String iphone = phone.getText().toString().trim();
			String metName = "\\d{1,5}";
			String metPhone = "1[358]\\d{9}";
			String metPassword = "\\d{1,5}";
			boolean c = username.matches(metName);
			boolean a = password.matches(metPassword);
			boolean b = iphone.matches(metPhone);
			String sql = "select * from users where username = ? and password = ? and phone = ?";
			result = db.getReadableDatabase().rawQuery(sql,
					new String[] { username, password, iphone });
			if (username.equals("") && password.equals("") && iphone.equals("")) {
				message("��д����Ϣ!!!");
			} else if (password.equals("")) {
				message("��д����Ϣ!!!");
			} else if (iphone.equals("")) {
				message("��д����Ϣ!!!");
			} else if (!a) {
				message("�����ʽ����ȷ...");
			} else if (!b) {
				message("�ֻ������ʽ����ȷ...");
			} else if (!c) {
				message("�û�����ʽ����ȷ...");
			} else if (result.moveToFirst()) {
				if (username.equals(result.getString(result
						.getColumnIndex("username")))
						&& password.equals(result.getString(result
								.getColumnIndex("password")))) {
					message("�˺��Ѿ�����!");
					break;
				}
			} else {
				option = new OptionTable(db.getWritableDatabase());
				option.UserInsert(username, password, iphone);
				user.setText("");
				pwd.setText("");
				phone.setText("");
				message("ע��ɹ�!");
				startActivity(new Intent(RegActivity.this, LoginActivity.class));
				finish();
			}
			break;
		case R.id.LoginConfig:
			startActivity(new Intent(RegActivity.this, RegActivity.class));
			finish();
			break;
		case R.id.loginback:
			startActivity(new Intent(RegActivity.this, LoginActivity.class));
			finish();
			break;
		}
	}

	public void message(String text) {
		Toast toast = Toast.makeText(RegActivity.this, text, 1000);
		toast.show();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

}
