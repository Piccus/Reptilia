package com.piccus.tools;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

public abstract class DBControl {
	//默认数据库名称
	private static String dbname = "jdbc:sqlite:news.db";
	
	//headlinenews表存在判断SQL
	private static String headlinetableExistSql = "SELECT count(*) FROM sqlite_master WHERE type='table' AND name='HeadlineNews';";
	//headlinenews表创建SQL
	private static String headlinetableCreateSql = "CREATE TABLE HeadlineNews(title varchar(100), url varchar(100), source varchar(20));";
	
	private static String headlineNewsExistSql = "select * from HeadlineNews where title='";
	
	private static String newsType = "";
	
	private static final int Sina = 0;
	
	private static final int Netease = 1;
	
	private static final int Sohu = 2;
	
	private static String getSinaSql = "select * from HeadlineNews where source = '新浪';";
	
	private static String getNeteaseSql = "select * from HeadlineNews where source = '网易';";
	
	private static String getSohuSql = "select * from HeadlineNews where source = '搜狐';";
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
				newsType = "新浪";
			if(type == Netease)
				newsType = "网易";
			if(type == Sohu)
				newsType = "搜狐";
			for(Entry<String, String> entry : hm.entrySet()){
				String searchData = headlineNewsExistSql + entry.getKey() + "';" ;
				rs = stat.executeQuery(searchData);
				if(!(rs.next())){
					String insertData = "INSERT INTO HeadlineNews VALUES('" + entry.getKey() + "', '" + entry.getValue() + "', '" + newsType + "');";
					stat.executeUpdate(insertData);
					
				}
			}
			rs.close();
			stat.close();
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
	public static void changeDBName(String databasename){
		DBControl.dbname = "jdbc:sqlite:" + databasename + ".db";
	}
	
	public static Vector searchNews(int sinaMark, int neteaseMark, int sohuMark){
		Vector vector = new Vector();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(dbname);
			Statement stat = conn.createStatement();
			if(sinaMark == 1){
				ResultSet rs = stat.executeQuery(getSinaSql);
				while(rs.next()){
					vector.addElement(rs.getString("title") + "   " + rs.getString("url") + "   " + rs.getString("source"));
				}
			}
			if(neteaseMark == 1){
				ResultSet rs = stat.executeQuery(getNeteaseSql);
				while(rs.next()){
					vector.addElement(rs.getString("title") + "   " + rs.getString("url") + "   " + rs.getString("source"));
				}
			}
			if(sohuMark == 1){
				ResultSet rs = stat.executeQuery(getSohuSql);
				while(rs.next()){
					vector.addElement(rs.getString("title") + "   " + rs.getString("url") + "   " + rs.getString("source"));
				}
			}
			stat.close();
			conn.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
			return vector;
			}
}
