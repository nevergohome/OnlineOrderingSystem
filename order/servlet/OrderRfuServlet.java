package com.onlineordering.order.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.order.service.IOrderService;
import com.onlineordering.order.service.OrderService;

@WebServlet("/orderRfuServlet")
public class OrderRfuServlet extends HttpServlet{
	IOrderService os = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderId = req.getParameter("id");
		int st = 0;
		os.manageOrder(Integer.valueOf(orderId), st);
		req.getRequestDispatcher("/orderQueryServlet").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
