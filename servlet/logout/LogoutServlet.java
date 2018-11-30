package com.onlineordering.servlet.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 退出直接让当前的Session无效，跳转页面
		 */
		req.getSession().invalidate();
		/*
		 * ./往上一个文件夹路径
		 */
		resp.sendRedirect("./login.jsp");
	}
	
	

}
