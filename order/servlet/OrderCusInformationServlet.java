package com.onlineordering.order.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.util.ToolUtil;
import com.onlineordering.order.dao.OrderDao;
import com.onlineordering.order.service.IOrderService;
import com.onlineordering.order.service.OrderService;
import com.onlineordering.po.Customer;
import com.onlineordering.po.Ordering;

@WebServlet("/orderCusInformationServlet")
public class OrderCusInformationServlet extends HttpServlet {

	IOrderService os = new OrderService();
	Ordering o=new Ordering();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String remark=req.getParameter("remark");
		String money = req.getParameter("totalmoney");
		o.setOrderTotal(Integer.parseInt(money));
		o.setRemark(remark);
        //o.setOrderTime(TimeStamp(ToolUtil.getCurrentTime()));
		os.createOrder(o);
		req.getRequestDispatcher("/customerOrderQueryServlet").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nums = req.getParameter("nums");
		o.setMenuNum(nums);
		String numids = req.getParameter("numids");
		o.setMenuIds(numids);
		String money = req.getParameter("moneytotal");
		String admid = req.getParameter("admid");
		o.setAdmId(Integer.parseInt(admid));
		Customer cus = (Customer) req.getSession().getAttribute("user");
		o.setCusId(cus.getCusId());
		String[] numid = nums.split(",");
		List<String> numList= new ArrayList<>(Arrays.asList(numid));
		List<Map<String, Object>> menuList = os.findMenuIds(numids);
		Map<String, Object> orderinforMap = os.findOrderInfor(cus, admid);
		String s=new String();
		String s1=",";
		Iterator<Map<String, Object>> it=menuList.iterator();
		while(it.hasNext()) {
			s+=it.next().get("menuPrice")+s1;
		}
	    s=s.substring(0, s.length()-1);
	    o.setMenuPrices(s);
		req.setAttribute("menuList", menuList);
		req.setAttribute("orderinforMap", orderinforMap);
		req.setAttribute("moneytotal", money);
		req.setAttribute("nums", numList);
		req.setAttribute(numids, numids);
		req.setAttribute("numsmap", nums);
		req.getRequestDispatcher("user/cusorderinformation.jsp").forward(req, resp);
	}
	
}
