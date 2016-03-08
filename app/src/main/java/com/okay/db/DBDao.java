package com.okay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBDao implements DBService {

	private DBOpenHelper helper = null;
	private String table=null;

	public DBDao(Context context,String table) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenHelper(context);
		this.table=table;
	}

	@Override
	public boolean add(ContentValues values) {
		// TODO Auto-generated method stub
		boolean flag = false;
		SQLiteDatabase database = null;
		long id = -1;
		try {
			database = helper.getWritableDatabase();
			id = database.insert(table, null, values);
			flag = (id != -1 ? true : false);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean delete(String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		boolean flag = false;
		SQLiteDatabase database = null;
		int count = 0;
		try {
			database = helper.getWritableDatabase();
			count = database.delete(table, whereClause, whereArgs);
			flag = (count > 0 ? true : false);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public boolean update(ContentValues values, String whereClause,
			String[] whereArgs) {
		boolean flag = false;
		SQLiteDatabase database = null;
		int count = 0;// 影响数据库的行数
		try {
			database = helper.getWritableDatabase();
			count = database.update(table, values, whereClause, whereArgs);
			flag = (count > 0 ? true : false);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public Map<String, String> mapView(String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		// select 返回的列的名称(投影查询) from
		SQLiteDatabase database = null;
		Cursor cursor = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			database = helper.getReadableDatabase();
			cursor = database.query(true, table, null, selection,
					selectionArgs, null, null, null, null);
			int cols_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int i = 0; i < cols_len; i++) {
					String cols_name = cursor.getColumnName(i);
					String cols_value = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return map;
	}

	@Override
	public List<Map<String, String>> listViews(String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		SQLiteDatabase database = null;
		Cursor cursor = null;
		try {
			database = helper.getReadableDatabase();
			cursor = database.query(false, table, null, selection,
					selectionArgs, null, null, null, null);
			int cols_len = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = cursor.getColumnName(i);
					String cols_value = cursor.getString(cursor
							.getColumnIndex(cols_name));
					if (cols_value == null) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return list;
	}

}
