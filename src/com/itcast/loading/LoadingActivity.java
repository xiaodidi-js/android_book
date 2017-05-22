package com.itcast.loading;

import java.util.Timer;
import java.util.TimerTask;

import com.itcast.wechat.LoginActivity;
import com.itcast.wechat.MeActivity;
import com.itcast.wechat.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @version 1.0
 */
public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading);
		ViewUtils.inject(this);
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					startActivity(new Intent(LoadingActivity.this,
							MeActivity.class));
					finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
