package com.piccus.tools;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;

public abstract class DBControl {
	//默认数据库名称
	private static String dbname = "jdbc:sqlite:news.db";
	//baidunews表存在判断SQL
	private static String baidutableExistSql = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='BaiduNews';";
	//baidunews表创建SQL
	private static String baidutableCreateSql = "CREATE TABLE BaiduNews(title varchar(50), url varchar(100));" ;
	//headlinenews表存在判断SQL
	private static String headlinetableExistSql = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='HeadlineNews';";
	//headlinenews表创建SQL
	private static String headlinetableCreateSql = "CREATE TABLE HeadlineNews(title varchar(50), url varchar(100), source varchar(20));";
	
	private static String newsType = "";
	
	private static final int Sina = 0;
	
	private static final int Netease = 1;
	
	private static final int Sohu = 2;
	
	/*
	 * @param: HashMap
	 * @Author: Piccus
	 * @Description: 存储baidunews到SQlite 
	 */
	public static void saveBaiduNews(HashMap<String, String> hm){
		try
		{
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection(dbname);
			
			Statement stat = conn.createStatement();
			
			ResultSet rs = stat.executeQuery(baidutableExistSql);
			
			if(rs.getString(1).equals("0"))
				stat.executeUpdate(baidutableCreateSql);
				
			for(Entry<String, String> entry : hm.entrySet()){
				String insertData = "INSERT INTO BaiduNews VALUES('" + entry.getKey() + "', '" + entry.getValue() + "');";
				stat.executeUpdate(insertData);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * @param: HashMap, newstype
	 * @Author: Piccus
	 * @Description: 存储headlinenews到SQlite 
	 */
	public static void saveHeadlineNews(HashMap<String, String> hm, int type){
		try
		{
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection(dbname);
			
			Statement stat = conn.createStatement();
			
			ResultSet rs = stat.executeQuery(headlinetableExistSql);
			
			if(rs.getString(1).equals("0"))
				stat.executeUpdate(headlinetableCreateSql);
			
			if(type == Sina)
				newsType = "Sina";
			if(type == Netease)
				newsType = "Netease";
			if(type == Sohu)
				newsType = "Sohu";
			for(Entry<String, String> entry : hm.entrySet()){
				String insertData = "INSERT INTO HeadlineNews VALUES('" + entry.getKey() + "', '" + entry.getValue() + "', '" + newsType + "');";
				stat.executeUpdate(insertData);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * @param databasename
	 * @Author: Piccus
	 * @Description: 更改存储数据库
	 */
	public void changeDBName(String databasename){
		DBControl.dbname = "jdbc:sqlite:" + databasename;
	}
}
