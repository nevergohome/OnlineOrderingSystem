package com.onlineordering.customer.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.customer.service.CustomerService;
import com.onlineordering.customer.service.ICustomerService;
import com.onlineordering.po.Customer;

@WebServlet("/customerModifyServlet")
public class CustomerModifyServlet extends HttpServlet{
	private ICustomerService cus = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cusId = req.getParameter("id");
		Map<String, Object> mc = cus.findCustomerInfo(Integer.valueOf(cusId));
		req.setAttribute("cus", mc);
		req.getRequestDispatcher("manager/modifycustomer.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cusId = req.getParameter("id");
		String cusName = req.getParameter("cusName");
		String cusPwd = req.getParameter("cusPwd");
		String cusAdr = req.getParameter("cusAdr");
		String cusPnum = req.getParameter("cusPnum");
		Customer cs = new Customer();
		cs.setCusId(Integer.valueOf(cusId));
		cs.setCusName(cusName);
		cs.setCusPwd(cusPwd);
		cs.setCusAdr(cusAdr);
		cs.setCusPnum(cusPnum);
		cus.updateCustomer(cs);
		req.getRequestDispatcher("/manageQueryServlet").forward(req, resp);
	}
	

}
