package com.itcast.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itcase.bean.DBOpenHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MessageActivity extends Activity implements OnClickListener {

	private View view;
	private Intent intent;
	@ViewInject(R.id.SeBack)
	private TextView SBack;
	private Integer name = R.id.name;
	private Integer phone = R.id.phone;
	private Integer email = R.id.email;
	private Cursor cursor = null;
	@ViewInject(R.id.list)
	private ListView listview;
	// 数据库
	private DBOpenHelper db = null;
	private LayoutInflater mInflater;
	List<Map<String, Object>> listData;
	private SimpleAdapter adapter;
	private String[] from = new String[] {"name", "phone", "email" };
	private int[] to = new int[] {name, phone, email };


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.message);
		ViewUtils.inject(this);
		initIcon();
		db = new DBOpenHelper(this);
		SBack.setOnClickListener(this);
		listData = new ArrayList<Map<String, Object>>();
		// 建立SimpleAdapter，将from和to对应起来
		// sql查询语句
		String sql = "select * from firends";
		cursor = db.getReadableDatabase().rawQuery(sql, null);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", cursor.getString(1));
			map.put("phone", cursor.getString(2));
			map.put("email", cursor.getString(3));
			listData.add(map);
		}
		adapter = new SimpleAdapter(this, listData, R.layout.item_meg, from, to);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				HashMap<String, String> map = (HashMap<String, String>) listview
						.getItemAtPosition(position);
				String name = map.get("name");
				String phone = map.get("phone");
				String email = map.get("email");
				
				// 显式跳转
				intent = new Intent();
				for(int i = 0;i<map.size();i++){
					intent.setClass(MessageActivity.this, CallMessage.class);
					intent.putExtra("name", name);
					intent.putExtra("phone", phone);
					intent.putExtra("email", email);
					startActivity(intent);
					finish();
				}
			}
		});
		
	}

	public void Messages(String text) {
		Toast.makeText(this, text, 1000).show();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void initIcon() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		SBack.setTypeface(iconfont);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(MessageActivity.this, MeActivity.class));
			this.finish();
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.SeBack:
			startActivity(new Intent(MessageActivity.this, MeActivity.class));
			finish();
			break;
		}
	}

}
