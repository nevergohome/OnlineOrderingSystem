package com.onlineordering.order.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.order.service.IOrderService;
import com.onlineordering.order.service.OrderService;
import com.onlineordering.po.Admin;

@WebServlet("/orderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet{

	
	IOrderService os = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin ad = (Admin) req.getSession().getAttribute("user");
		int state = 3;
		List<Map<String,Object>> orderList = os.findAll(ad.getAdmId(), state);
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("manager/historyorder.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
}
}
