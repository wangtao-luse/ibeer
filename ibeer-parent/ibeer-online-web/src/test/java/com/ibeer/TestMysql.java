package com.ibeer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMysql {	
public static void main(String[] args) {
  //1.声明Connection对象
	Connection con = null;
  //2.驱动程序名
	String driver = "com.mysql.cj.jdbc.Driver";
  //3.URL指向要访问的数据库名
	String url = "jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";
  //4.用户名
	String user = "root";
  //5.密码
	String password = "root";
	try {
		//1.加载驱动程序
		Class.forName(driver);
		//2.获取连接对象
		con=DriverManager.getConnection(url, user, password);		
		System.out.println(con);
		//3.创建statement类对象，用来执行SQL语句
		Statement createStatement = con.createStatement();
	
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if(con!=null) {
				con.close();
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
