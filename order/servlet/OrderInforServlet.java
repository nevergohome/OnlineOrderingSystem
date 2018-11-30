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

@WebServlet("/orderInforServlet")
public class OrderInforServlet extends HttpServlet {

	
	IOrderService os = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderId = req.getParameter("id");
		Map<String,Object> orderinforMap = os.findorderInfor(Integer.valueOf(orderId));
		List<Map<String, Object>> ordernumList = os.findNum(Integer.valueOf(orderId));
		List<Map<String, Object>> ordermenuList = os.findMenuName(Integer.valueOf(orderId));
		
		req.setAttribute("orderMap", orderinforMap);
		req.setAttribute("ordermenuList", ordermenuList);
		req.setAttribute("ordernumList", ordernumList); 
		req.getRequestDispatcher("manager/orderinformation.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	
}
