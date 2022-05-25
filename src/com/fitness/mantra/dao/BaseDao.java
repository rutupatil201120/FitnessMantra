package com.fitness.mantra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	private static String dbUrl = "jdbc:mysql://localhost:3306/gym";
	private static String dbUname = "root";
	private static String dbPassword = "test123";
	private static String dbDriver = "com.mysql.cj.jdbc.Driver";

	private static void loadDriver() {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			loadDriver();
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
