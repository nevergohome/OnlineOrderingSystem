package com.onlineordering.customer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.customer.service.CustomerService;
import com.onlineordering.customer.service.ICustomerService;

@WebServlet("/customerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet{
    
	private ICustomerService cs = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cusId = req.getParameter("id");
		cs.deleteCustomer(cusId);
		req.getRequestDispatcher("/customerQueryServlet").forward(req, resp);
	}
	
}
