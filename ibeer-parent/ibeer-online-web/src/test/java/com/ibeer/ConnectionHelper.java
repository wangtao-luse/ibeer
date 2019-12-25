package com.ibeer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
private final static String DRIVER ="com.mysql.cj.jdbc.Driver";
private final static String URL = "jdbc:mysql://localhost:3306/ibeer?serverTimezone=UTC";
private final static String USER="root";
private final static String PASSWORD="root";
private static Connection con;
static {
	//1.加载驱动程序
			try {
				Class.forName(DRIVER);
				 con = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
}
public static Connection getConnection() {
	return con;
}
}
