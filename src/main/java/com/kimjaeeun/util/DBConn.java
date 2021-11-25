package com.kimjaeeun.util;

import java.sql.Connection;
import java.sql.DriverManager;

//import oracle.jdbc.driver.OracleDriver;

//437p참고
public class DBConn {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			//OracleDriver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@db.kimjaeeun.com:1521:xe", "JSPUSER", "JSPUSER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
}