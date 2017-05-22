package com.itcast.wechat;

import com.itcase.bean.OptionTable;
import com.itcase.bean.DBOpenHelper;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.insertBack)
	private TextView insertBack;
	@ViewInject(R.id.name)
	private EditText name;
	@ViewInject(R.id.telphone)
	private EditText telphone;
	@ViewInject(R.id.address)
	private EditText address;
	@ViewInject(R.id.qq)
	private EditText qq;
	@ViewInject(R.id.submit)
	private Button submit;
	// ����SaveTable
	private OptionTable table = null;
	// ���ݿ�
	private DBOpenHelper db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.insert);
		ViewUtils.inject(this);
		// ���ӵ���¼�
		insertBack.setOnClickListener(this);
		submit.setOnClickListener(this);
		initIcon();
		db = new DBOpenHelper(this);
	}

	public void clearEdit() {
		name.setText("");
		telphone.setText("");
		address.setText("");
		qq.setText("");
	}

	public void message(String text) {
		Toast toast = Toast.makeText(ContactActivity.this, text, 1000);
		toast.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent myIntent = new Intent();
			myIntent = new Intent(ContactActivity.this, MeActivity.class);
			startActivity(myIntent);
			this.finish();
		}
		return false;
	}

	private void initIcon() {
		Typeface iconfont = Typeface.createFromAsset(getAssets(),
				"font/iconfont.ttf");
		insertBack.setTypeface(iconfont);
	}


	@Override
	public void onClick(View v) {
		String TheName = name.getText().toString().trim();
		String ThePhone = telphone.getText().toString().trim();
		String TheAddress = address.getText().toString().trim();
		String Theqq = qq.getText().toString().trim();

		String metPhone = "1[358]\\d{9}";
		String metEmail = "[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{1,3})+";
		String metQQ = "[1-9][0-9]{4,14}";
		boolean telephone = ThePhone.matches(metPhone);
		boolean qqemail = TheAddress.matches(metEmail);
		boolean qqNumber = Theqq.matches(metQQ);
		switch (v.getId()) {
		case R.id.insertBack:
			startActivity(new Intent(ContactActivity.this, MeActivity.class));
			finish();
			break;
		case R.id.submit:
			if (TheName.equals("")) {
				message("��������Ϣ!лл!");
			} else if (ThePhone.equals("")) {
				message("������绰����!лл!");
			} else if (TheAddress.equals("")) {
				message("�����������ַ!лл!");
			} else if (Theqq.equals("")) {
				message("������QQ����!лл!");
			} else if (!telephone) {
				message("�ֻ������ʽ����ȷ");
			} else if (!qqemail) {
				message("�����ַ��ʽ����ȷ");
			} else if (!qqNumber) {
				message("QQ�����ʽ����ȷ");
			} else {
				table = new OptionTable(db.getWritableDatabase());
				table.insert(TheName, ThePhone, TheAddress, Theqq);
				message("���ӳɹ�");
				startActivity(new Intent(ContactActivity.this,MessageActivity.class));
				// ����ı���
				clearEdit();
			}
			break;
		}
	}
}
