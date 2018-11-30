package com.onlineordering.customer.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.customer.service.CustomerService;
import com.onlineordering.customer.service.ICustomerService;
import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;

@WebServlet("/customerQueryServlet")
public class CustomerQueryServlet extends HttpServlet{

	private ICustomerService cs = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = req.getParameter("cusname");
		Customer ct = new Customer();
		List cusList = cs.findCustomerInfo(value);
		req.setAttribute("cusList", cusList);
		req.getRequestDispatcher("manager/customermanage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doGet(req, resp);
	}

}
