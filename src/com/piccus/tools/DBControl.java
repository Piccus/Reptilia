package com.piccus.tools;

import org.sqlite.JDBC;
import java.sql.*;

public abstract class DBControl {
	
	public static void main(String[] args) {
		try
		{
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:demo.db");
			
			Statement stat = conn.createStatement();
			stat.executeUpdate("create table users(name varchar(20) primary key);");
			
			stat.executeUpdate("insert into users values('piccus');");
			conn.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
