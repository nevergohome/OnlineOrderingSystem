package com.onlineordering.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.login.service.ILoginService;
import com.onlineordering.login.service.LoginService;
import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;

@WebServlet("/registerrServlet")
public class RegisterrServlet extends HttpServlet{
  
	
	private ILoginService ls = new LoginService();

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String role = req.getParameter("register");
		Customer customer = new Customer();
		if("user".equals(role)){
			String cusId = req.getParameter("cusId");
			String cusName = req.getParameter("cusName");
			String cusPwd = req.getParameter("cusPwd");
			String cusAdr = req.getParameter("cusAdr");
			String cusPnum = req.getParameter("cusPnum");
			customer.setCusId(Integer.valueOf(cusId));
			customer.setCusName(cusName);
			customer.setCusPwd(cusPwd);
			customer.setCusPnum(cusPnum);
			customer.setCusAdr(cusAdr);
			ls.AddCustomer(customer);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		else{
			String admId = req.getParameter("admId");
			String admName = req.getParameter("admName");
			String admPwd = req.getParameter("admPwd");
			String admPnum = req.getParameter("admPnum");
			String admAdr = req.getParameter("admAdr");
			String admState = req.getParameter("admState");
			String admPostage = req.getParameter("admPostage");
			String admTableware = req.getParameter("admTableware");
			Admin admin = new Admin();
			admin.setAdmId(Integer.valueOf(admId));
			admin.setAdmName(admName);
			admin.setAdmPwd(admPwd);
			admin.setAdmAdr(admAdr);
			admin.setAdmPnum(admPnum);
		    admin.setAdmPostage(Integer.valueOf(admPostage));
		    admin.setAdmTableware(Integer.valueOf(admTableware));
		    admin.setAdmState(Integer.valueOf(admState));
			ls.AddAdmin(admin);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
	
}
