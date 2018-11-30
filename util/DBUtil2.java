 package com.onlineordering.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;
/**
 * 数据库连接，工具类，及关闭方法�?
 * @author Administrator
 *
 */
public class DBUtil2 {
	//private static final String DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;			//驱动类类�?
	private static final String DBDRIVER = "com.mysql.jdbc.Driver" ;			//驱动类类�?
	//private static final String DBURL = "jdbc:sqlserver://192.168.17.136:1433;DatabaseName=HP_testDB";//连接URL
	private static final String DBURL = "jdbc:mysql://localhost:3306/orderingsystem";//连接URL
    private static final String DBUSER = "root" ;								//数据库用户名
    private static final String DBPASSWORD = "root";							//数据库密�?
	public static Connection getConnection(){
		Connection conn = null;													//声明�?��连接对象
		try {
			Class.forName(DBDRIVER);											//注册驱动
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);		//获得连接对象
		} catch (ClassNotFoundException e) {									//捕获驱动类无法找到异�?
			e.printStackTrace();										
		} catch (SQLException e) {												//捕获SQL异常
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {//关闭连接对象
		if(conn != null) {				//如果conn连接对象不为�?
			try {
				conn.close();			//关闭conn连接对象对象
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement pstmt) {//关闭预处理对�?
		if(pstmt != null) {				//如果pstmt预处理对象不为空
			try {
				pstmt.close();			//关闭pstmt预处理对�?
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Statement stmt) {//关闭预处理对�?
		if(stmt != null) {				//如果stmt对象不为�?
			try {
				stmt.close();			//关闭stmt处理对象
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs) {//关闭结果集对�?
		if(rs != null) {				//如果rs结果集对象不为null
			try {
				rs.close();				//关闭rs结果集对�?
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	
}
