package com.onlineordering.menu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.onlineordering.menu.service.IMenuService;
import com.onlineordering.menu.service.MenuService;
import com.onlineordering.po.Admin;
import com.onlineordering.po.Menu;

@WebServlet("/menuQueryServlet")
public class MenuQueryServlet extends HttpServlet{
	
	private IMenuService ms = new MenuService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = req.getParameter("menuname");
		Admin ad = (Admin) req.getSession().getAttribute("user");
		List menuList = ms.findMenuInfo(ad.getAdmId(),value);
		req.setAttribute("user", ad);
		req.setAttribute("menuList", menuList);
		req.getRequestDispatcher("manager/menumanage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

}
