package com.onlineordering.menu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.menu.service.IMenuService;
import com.onlineordering.menu.service.MenuService;

@WebServlet("/menuDeleteServlet")
public class MenuDeleteServlet extends HttpServlet{

	private IMenuService ms = new MenuService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menuId = req.getParameter("id");
		ms.deleteMenu(menuId);
		req.getRequestDispatcher("/menuQueryServlet").forward(req, resp);
	}

	

}
