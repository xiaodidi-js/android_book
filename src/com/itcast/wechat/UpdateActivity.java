package com.itcast.wechat;

import com.itcase.bean.OptionTable;
import com.itcase.bean.DBOpenHelper;
import com.itcase.web.AboutActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.UpdateId)
	private TextView Textid;
	@ViewInject(R.id.UpdateBack)
	private TextView Back;
	@ViewInject(R.id.name)
	private EditText name;
	@ViewInject(R.id.telphone)
	private EditText telphone;
	@ViewInject(R.id.address)
	private EditText address;
	@ViewInject(R.id.qq)
	private EditText qq;
	@ViewInject(R.id.UpdateSubmit)
	private Button submit;
	private DBOpenHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.update);
		ViewUtils.inject(this); // 注入view和事件
		initIcon();
		submit.setOnClickListener(this);
		Back.setOnClickListener(this);
	}

	public void initIcon() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		Back.setTypeface(iconfont);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent myIntent = new Intent();
			myIntent = new Intent(UpdateActivity.this, MeActivity.class);
			startActivity(myIntent);
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void clearEdit() {
		name.setText("");
		telphone.setText("");
		address.setText("");
		qq.setText("");
	}

	public void message(String text) {
		Toast toast = Toast.makeText(UpdateActivity.this, text, 1000);
		toast.show();
	}


	@Override
	public void onClick(View v) {
		String str_id = Textid.getText().toString();
		String TheName = name.getText().toString();
		String ThePhone = telphone.getText().toString();
		String TheAddress = address.getText().toString();
		String Theqq = qq.getText().toString();
		switch (v.getId()) {
		case R.id.UpdateBack:
			startActivity(new Intent(UpdateActivity.this, MeActivity.class));
			finish();
			break;
		case R.id.UpdateSubmit:
			if (str_id.equals("") && TheName.equals("") && ThePhone.equals("")
					&& TheAddress.equals("") && Theqq.equals("")) {
				message("请输入信息!谢谢!");
			} else {
				db = new DBOpenHelper(this);
				OptionTable Update = new OptionTable(db.getWritableDatabase());
				int a = Integer.parseInt(str_id);
				Update.update(a, TheName, ThePhone, TheAddress, Theqq);
				message("修改成功");
				startActivity(new Intent(UpdateActivity.this,MessageActivity.class));
				finish();
				// 清空文本框
				clearEdit();
			}
			break;
		}
	}

}
