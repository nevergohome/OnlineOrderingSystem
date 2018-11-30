package com.onlineordering.util;

public class DBUtilExTest {

	public static void main(String args[]) throws Exception{
		DBUtil db = new DBUtil();
		String sql = "insert into course(couName) values('Java Web')";
		//db.execute(sql);
		String sql1 = "insert into course(couName) values(?)";
		//db.execute(sql1, new Object[]{"军事理论"});
		String sql2 = "delete from course where couName = ?";
		//db.execute(sql2, new Object[]{"UML"});
		String sql3 = "update course set couName = ? where CouId = ?";
		//db.execute(sql3, new Object[]{ "计算机组成原�?,"4"});
		
	}
}
