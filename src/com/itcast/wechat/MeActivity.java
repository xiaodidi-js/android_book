package com.itcast.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.itcase.web.AboutActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MeActivity extends Activity implements OnClickListener {

	private View view;
	@ViewInject(R.id.insertIcon)
	private TextView insertIcon;
	@ViewInject(R.id.updateIcon)
	private TextView updateIcon;
	@ViewInject(R.id.deleteIcon)
	private TextView deleteIcon;
	@ViewInject(R.id.aboutIcon)
	private TextView aboutIcon;
	@ViewInject(R.id.MsmIcon)
	private TextView MsmIcon;
	@ViewInject(R.id.insert)
	private LinearLayout insert;
	@ViewInject(R.id.showMsm)
	private LinearLayout showMsm;
	@ViewInject(R.id.delete)
	private LinearLayout delete;
	@ViewInject(R.id.update)
	private LinearLayout update;
	@ViewInject(R.id.about)
	private LinearLayout about;
	@ViewInject(R.id.title)
	private RelativeLayout title;
	private boolean flag = false;
	private SharedPreferences shared;
	private String saveOfUserName;
	private static final String PREFS_NAME = "保存用户信息";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me);
		ViewUtils.inject(this);
		initIcon();
		insert.setOnClickListener(this);
		delete.setOnClickListener(this);
		update.setOnClickListener(this);
		about.setOnClickListener(this);
		showMsm.setOnClickListener(this);
		title.setOnClickListener(this);
		serverDemo();
	}

	private void initIcon() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		insertIcon.setTypeface(iconfont);
		updateIcon.setTypeface(iconfont);
		deleteIcon.setTypeface(iconfont);
		aboutIcon.setTypeface(iconfont);
		MsmIcon.setTypeface(iconfont);
	}

	public void ShowToast(String text) {
		Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
		toast.show();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(false);
			return true;
		}
		return false;
	}

	public void serverDemo() {
		Timer demo = new Timer();
		demo.schedule(new TimerTask() {
			@Override
			public void run() {
				shared = getSharedPreferences(PREFS_NAME, Context.MODE_WORLD_READABLE);
				saveOfUserName = shared.getString("author", saveOfUserName);
				if (saveOfUserName != null && saveOfUserName != "") {
					shared.edit().clear().commit();
					startActivity(new Intent(MeActivity.this, LoginActivity.class));
					finish();
				}
			}
		}, 60 * 1 * 5000);
		
	}

	private void exitBy2Click() {
		Timer tExit = null;
		if (!flag) {
			flag = true; // 准备退出
			message("再按一次退出程序");
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					flag = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			finish();
			System.exit(0);// 退出程序
		}
	}

	public void message(String text) {
		Toast toast = Toast.makeText(MeActivity.this, text, 1000);
		toast.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.insert:
			startActivity(new Intent(MeActivity.this, ContactActivity.class));
			finish();
			break;
		case R.id.showMsm:
			startActivity(new Intent(MeActivity.this, MessageActivity.class));
			finish();
			break;
		case R.id.delete:
			startActivity(new Intent(MeActivity.this, DeleteActivity.class));
			finish();
			break;
		case R.id.update:
			startActivity(new Intent(MeActivity.this, UpdateActivity.class));
			finish();
			break;
		case R.id.about:
			startActivity(new Intent(MeActivity.this, AboutActivity.class));
			finish();
			break;
		case R.id.title:
			startActivity(new Intent(MeActivity.this, AboutActivity.class));
			finish();
			break;
		}
	}
}
