package com.onlineordering.order.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.order.service.IOrderService;
import com.onlineordering.order.service.OrderService;
import com.onlineordering.po.Admin;

@WebServlet("/admNameQueryServlet")
public class AdmNameQueryServlet extends HttpServlet {
	IOrderService os = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = req.getParameter("adminname");
		List<Map<String,Object>> adminList = os.findAdminInfo(value);
		req.setAttribute("adminList", adminList);
		req.getRequestDispatcher("/user/merchant.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
}
