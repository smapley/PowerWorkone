package com.okay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static String name = "questionnaire.db";// ��ʾ���ݿ������
	private static int version = 1;// ��ʾ���ݿ�İ汾����

	public DBOpenHelper(Context context) {
		super(context, name, null, version);
	}

	// �����ݿⴴ����ʱ���ǵ�һ�α�ִ��,��ɶ����ݿ�ı�Ĵ���
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// ֧�ֵ��������ͣ��������ݣ��ַ������ͣ��������ͣ������Ƶ��������ͣ�
		String sql0 = "create table Team(id integer primary key autoincrement,Tm_id varchar(30),Name varchar(30),Tm_Leader varchar(30),Number int)";
		String sql1 = "create table Task(id integer primary key autoincrement,Name	varchar(30),Start_Time datetime,End_Time datetime,Type varchar(30),Created_time datetime,Tm_id varchar(30))";
		db.execSQL(sql0);
		db.execSQL(sql1);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
