package com.itcast.wechat;

import com.itcase.bean.OptionTable;
import com.itcase.bean.DBOpenHelper;
import com.itcase.web.AboutActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.DBack)
	private TextView DBack;
	@ViewInject(R.id.delete)
	private Button delete;
	@ViewInject(R.id.DeleteId)
	private EditText edittext;
	private DBOpenHelper db = null;
	private Cursor result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.delete);
		ViewUtils.inject(this);
		DBack.setOnClickListener(this);
		delete.setOnClickListener(this);
		initIcon();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(DeleteActivity.this, MeActivity.class));
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void message(String text) {
		Toast toast = Toast.makeText(DeleteActivity.this, text, 1000);
		toast.show();
	}

	private void initIcon() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		DBack.setTypeface(iconfont);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.DBack:
			startActivity(new Intent(DeleteActivity.this, MeActivity.class));
			finish();
			break;
		case R.id.delete:
			db = new DBOpenHelper(this);
			OptionTable delete = new OptionTable(db.getWritableDatabase());
			String NameStr = edittext.getText().toString();
			String sql = "select * from firends where name = ?";
			result = db.getReadableDatabase().rawQuery(sql,
					new String[] { NameStr });
			if (NameStr.equals("")) {
				message("请输入信息!谢谢!");
			} else if (result.moveToFirst()) {
				if (NameStr.equals(result.getString(result.getColumnIndex("id")))) {
					int a = Integer.parseInt(NameStr);
					delete.delete(a);
					message("成功删除");
					edittext.setText("");
					startActivity(new Intent(DeleteActivity.this,
							MessageActivity.class));
					finish();
					break;
				}
			} else {
				message("ID不存在或者已经删除!");
			}
			break;
		}
	}
}
