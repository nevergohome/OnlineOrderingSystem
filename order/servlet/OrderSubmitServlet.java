package com.onlineordering.order.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;

/**
 * Servlet implementation class OrderSubmitServlet
 */
@WebServlet("/OrderSubmitServlet")
public class OrderSubmitServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer cus = (Customer) request.getSession().getAttribute("user");
		String remark=request.getParameter("remark");
		String orderTotal=request.getParameter("orderTotal");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
