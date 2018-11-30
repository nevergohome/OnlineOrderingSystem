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

@WebServlet("/adminMenuServlet")
public class AdminMenuServlet extends HttpServlet{

	
	IOrderService os = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String admId = req.getParameter("id");
		String val = req.getParameter("menuname");
		List<Map<String, Object>> menuList = os.findMenuByadmId(Integer.valueOf(admId), val);
		req.setAttribute("menuList", menuList);
		req.setAttribute("admid", admId);
		req.getRequestDispatcher("/user/ordering.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
}
