package com.onlineordering.login.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.login.service.ILoginService;
import com.onlineordering.login.service.LoginService;
import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{

	private ILoginService ls = new LoginService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		if("admin".equals(role)){
			Admin t = new Admin();
			t.setAdmId(Integer.valueOf(username));
			t.setAdmPwd(password);
			t = ls.canLogin(t);
			if(null != t){
				req.getSession().setAttribute("user",t);
				req.getRequestDispatcher("manager/index.jsp").forward(req, resp);
			}
			else{
				req.getRequestDispatcher("loginre.jsp").forward(req, resp);
			}
		}
		else if ("customer".equals(role)){
			Customer st = new Customer();
			st.setCusId(Integer.valueOf(username));
			st.setCusPwd(password);
			st = ls.canLogin(st);
			if(null != st){
				req.getSession().setAttribute("user",st);
				req.getRequestDispatcher("user/index.jsp").forward(req, resp);
			}
			else{
				req.getRequestDispatcher("loginre.jsp").forward(req, resp);
			}
		}
			
	}
	
	
	

}
