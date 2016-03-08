package com.okay.db;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

public interface DBService {

	public boolean add(ContentValues values);
	
	public boolean delete(String whereClause, String[] whereArgs);
	
	public boolean update(ContentValues values, String whereClause, String[] whereArgs);
	
	public Map<String,String> mapView(String selection, String[] selectionArgs);
	
	public List<Map<String,String>> listViews(String selection, String[] selectionArgs);
}
