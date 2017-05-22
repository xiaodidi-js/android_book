package com.itcase.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.*;


public class OptionTable {
	//���ݿ�
	private SQLiteDatabase db = null;
	private DBOpenHelper help = null;
	private DBOpenHelper open;
	private Cursor result;
	//����
	private List<Map<String, Object>> listData;
	//����
	private static final String TABLENAME = "firends";
	
	/**
	 * @param db
	 */
	public OptionTable(SQLiteDatabase db){
		this.db = db;
	}
	
	/**
	 * ���ݲ��뷽��
	 * @param name
	 * @param phone
	 * @param address
	 * @param qq
	 */
	public void insert(String name,String phone, String address, String qq){
		String sql = "insert into firends (name,phone,address,qq) values ('"+ name +"','"+ phone +"','"+ address +"','"+ qq +"')";
		db.execSQL(sql);//ִ��Sql���
		db.close();//�ر����ݿ�
	}
	
	/**
	 * �û�ע�᷽��
	 * @param username
	 * @param password
	 * @param iphone
	 */
	public void UserInsert(String username,String password, String iphone){
		String sql = "insert into users (username,password,phone) values ('"+ username +"','"+ password +"','"+ iphone +"')";
		db.execSQL(sql);//ִ��Sql���
		db.close();//�ر����ݿ�
	}
	
	/**
	 * �������ݷ���
	 * @param id
	 * @param name
	 * @param phone
	 * @param address
	 * @param qq
	 */
	public void update(int id,String name,String phone, String address, String qq){
		String sql = "update firends SET name='"+ name +"',phone = '"+phone+"',address = '"+ address +"',qq = '"+ qq +"' where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
	/**
	 * ɾ�����ݷ���
	 * @param id
	 */
	public void delete(Integer id){
		String sql = "delete from firends where id = " + id;
		db.execSQL(sql);
		db.close();
	}
	
}
