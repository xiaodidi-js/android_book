package com.itcast.wechat;

import com.itcase.bean.DBOpenHelper;
import com.itcase.bean.OptionTable;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class CallMessage extends Activity implements OnClickListener {

	@ViewInject(R.id.CallName)
	private TextView CallName;
	@ViewInject(R.id.Number)
	private TextView Number;
	@ViewInject(R.id.Email)
	private TextView Email;
	@ViewInject(R.id.CallBack)
	private TextView CallBack;
	@ViewInject(R.id.call)
	private Button callConfig;
	Intent intent = null;
	private DBOpenHelper db = null;
	private Cursor result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callpage);
		ViewUtils.inject(this);
		callConfig.setOnClickListener(this);
		initIcon();
		TextDemo();
	}

	public void TextDemo() {
		intent = getIntent();
		String name = CallName.getText().toString();
		String Telephone = Number.getText().toString();
		String QQEmail = Email.getText().toString();
		name = intent.getStringExtra("name");
		Telephone = intent.getStringExtra("phone");
		QQEmail = intent.getStringExtra("email");
		CallName.setText(name);
		Number.setText(Telephone);
		Email.setText(QQEmail);
		
	}

	private void initIcon() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		CallBack.setTypeface(iconfont);
		CallBack.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.call:
			String Telephone = Number.getText().toString();
			Intent intent = new Intent();
			intent.setAction("android.intent.action.CALL");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.setData(Uri.parse("tel:" + Telephone));
			startActivity(intent);
			String name = CallName.getText().toString();
			break;
		case R.id.CallBack:
			startActivity(new Intent(CallMessage.this, MessageActivity.class));
			finish();
			break;
		}
	}
	
	public void Messages(String text) {
		Toast.makeText(this, text, 1000).show();
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		startActivity(new Intent(CallMessage.this,MessageActivity.class));
		finish();
		return true;
	}
	
}
//┏┓　　　┏┓  
//┏┛┻━━━┛┻┓  
//┃　　　　　　　┃ 　  
//┃　　　━　　　┃  
//┃　┳┛　┗┳　┃  
//┃　　　　　　　┃  
//┃　　　┻　　　┃  
//┃　　　　　　　┃  
//┗━┓　　　┏━┛  
//┃　　　┃ 神兽保佑　　　　　　　　  
//┃　　　┃ 代码无BUG！  
//┃　　　┗━━━┓  
//┃　　　　　　　┣┓  
//┃　　　　　　　┏┛  
//┗┓┓┏━┳┓┏┛  
//┃┫┫　┃┫┫  
//┗┻┛　┗┻┛
