package com.okay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static String name = "questionnaire.db";// 表示数据库的名称
	private static int version = 1;// 表示数据库的版本号码

	public DBOpenHelper(Context context) {
		super(context, name, null, version);
	}

	// 当数据库创建的时候，是第一次被执行,完成对数据库的表的创建
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// 支持的数据类型：整型数据，字符串类型，日期类型，二进制的数据类型，
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
