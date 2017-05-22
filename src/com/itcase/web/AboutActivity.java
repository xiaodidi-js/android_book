package com.itcase.web;

import com.itcast.wechat.LoginActivity;
import com.itcast.wechat.MeActivity;
import com.itcast.wechat.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.StaticLayout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * 设置关于一些本App和一些关于本人的有关信息
 * 
 * @version 1.0
 */
public class AboutActivity extends Activity implements OnClickListener{

	@ViewInject(R.id.aboutBack)
	private TextView aboutBack;
	@ViewInject(R.id.Logout)
	private Button Logout;
	private SharedPreferences shared;
	private String saveOfUserName;
	private static final String PREFS_NAME = "保存用户信息";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		ViewUtils.inject(this); // 注入view和事件
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		aboutBack.setTypeface(iconfont);
		aboutBack.setOnClickListener(this);
		Logout.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent myIntent = new Intent();
			myIntent = new Intent(AboutActivity.this, MeActivity.class);
			startActivity(myIntent);
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.aboutBack:
			startActivity(new Intent(AboutActivity.this, MeActivity.class));
			finish();
			break;
		case R.id.Logout:
			shared = getSharedPreferences(PREFS_NAME,
					Context.MODE_WORLD_READABLE);
			saveOfUserName = shared.getString("author", saveOfUserName);
			if (saveOfUserName != null && saveOfUserName != "") {
				shared.edit().clear().commit();
				startActivity(new Intent(AboutActivity.this, LoginActivity.class));
				finish();
			}
			break;
		}
	}
}
