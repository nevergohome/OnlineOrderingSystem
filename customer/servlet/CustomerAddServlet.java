package com.onlineordering.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.customer.service.CustomerService;
import com.onlineordering.customer.service.ICustomerService;
import com.onlineordering.po.Customer;

@WebServlet("/customerAddServlet")
public class CustomerAddServlet  extends HttpServlet {
  
	private ICustomerService cus = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("manager/addcustomer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cusName = req.getParameter("cusName");
		String cusPwd = req.getParameter("cusPwd");
		String cusAdr = req.getParameter("cusAdr");
		String cusPnum = req.getParameter("cusPnum");
		Customer cs = new Customer();
		cs.setCusName(cusName);
		cs.setCusPwd(cusPwd);
		cs.setCusAdr(cusAdr);
		cs.setCusPnum(cusPnum);
		cus.addCustomer(cs);
		req.getRequestDispatcher("/customerQueryServlet").forward(req, resp);
	}
	
}
