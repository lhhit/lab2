package com.library.connDatabase;

import java.sql.*;

public class BookDB {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	//连接数据库
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/BookDB","root", "123456");
			//conn = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_zgylab","o51jwxxzxm", "m0zkh22m1jzik352h3l5k11lkz5zhymmxxwz3mwj");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	//关闭数据库
	public void closeDatabase(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
