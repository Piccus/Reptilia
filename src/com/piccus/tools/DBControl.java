package com.piccus.tools;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;

public abstract class DBControl {
	//Ĭ�����ݿ�����
	private static String dbname = "jdbc:sqlite:baidunews.db";
	//������ж�SQL
	private static String tableExistSql = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='BaiduNews';";
	//����SQL
	private static String tableCreateSql = "CREATE TABLE BaiduNews(title varchar(50), url varchar(100));" ;
	
	/*
	 * @param: HashMap
	 * @Author: Piccus
	 * @Description: �洢���ݵ�SQlite 
	 */
	public static void Save(HashMap<String, String> hm){
		try
		{
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection(dbname);
			
			Statement stat = conn.createStatement();
			
			ResultSet rs = stat.executeQuery(tableExistSql);
			
			if(rs.getString(1).equals("0"))
				stat.executeUpdate(tableCreateSql);
				
			for(Entry<String, String> entry : hm.entrySet()){
				String insertData = "INSERT INTO BaiduNews VALUES('" + entry.getKey() + "', '" + entry.getValue() + "');";
				stat.executeUpdate(insertData);
			}
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
