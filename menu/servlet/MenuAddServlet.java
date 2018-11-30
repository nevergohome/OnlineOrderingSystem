package com.onlineordering.menu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.menu.service.IMenuService;
import com.onlineordering.menu.service.MenuService;
import com.onlineordering.po.Admin;
import com.onlineordering.po.Menu;

@WebServlet("/menuAddServlet")
public class MenuAddServlet extends HttpServlet {
	
	private IMenuService ms = new MenuService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("manager/addmenu.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin ad = (Admin) req.getSession().getAttribute("user");
		int admId = ad.getAdmId();
		String menuName = req.getParameter("menuName");
		String menuPrice = req.getParameter("menuPrice");
		String menuState = req.getParameter("menuState");
		Menu mn = new Menu();
		mn.setAdmId(admId);
		mn.setMenuName(menuName);
		mn.setMenuPrice(Integer.valueOf(menuPrice));
		mn.setMenuState(Integer.valueOf(menuState));
		ms.addMenu(mn);
		req.getRequestDispatcher("/menuQueryServlet").forward(req, resp);
	}

}
