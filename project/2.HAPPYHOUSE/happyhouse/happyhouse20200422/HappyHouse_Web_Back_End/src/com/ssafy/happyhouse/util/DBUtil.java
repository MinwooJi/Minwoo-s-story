package com.ssafy.happyhouse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
//	static final String URL = "jdbc:mysql://127.0.0.1:3305/ssafyweb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
//	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//	static final String ID = "ncia";
//	static final String PASSWORD = "root";
//
//	static {
//		try {
//			Class.forName(DRIVER);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, ID, PASSWORD);
//	}
	public static Connection getConnection() throws SQLException {
		// connection polling version -> 이론상 10배정도 속도빠름
		DataSource dataSource = null;
		try {
			Context iCtx = new InitialContext(); // 파일탐색기 열기
			Context ctx = (Context)iCtx.lookup("java:comp/env"); // root 디렉토리 찾기
			dataSource = (DataSource)ctx.lookup("jdbc/ssafy");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource.getConnection();
	}
	public static void close(AutoCloseable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
