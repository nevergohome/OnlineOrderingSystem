package com.onlineordering.order.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

@WebServlet("/statisServlet")
public class StatisServlet extends HttpServlet{
	
	IOrderService os = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Timestamp startDate = Timestamp.valueOf(req.getParameter("startdate"));
		Timestamp endDate = Timestamp.valueOf(req.getParameter("enddate"));
		SimpleDateFormat sdf = new SimpleDateFormat("YYY-MM-dd hh:mm:ss");
		Admin ad = (Admin) req.getSession().getAttribute("user");
		List<Map<String,Object>> statisList = os.findStatis(ad.getAdmId(), startDate, endDate);
		Map<String,Object> ordersumMap = os.sumOrder(ad.getAdmId(), startDate, endDate);
		//int sumMoney = os.sumMoney(ad.getAdmId(), startDate, endDate);
		req.setAttribute("statisList", statisList);
		req.setAttribute("orderSum", ordersumMap);
		req.getRequestDispatcher("manager/statistical.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	

}
