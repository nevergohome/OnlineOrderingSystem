package com.onlineordering.menu.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.onlineordering.menu.service.IMenuService;
import com.onlineordering.menu.service.MenuService;
import com.onlineordering.po.Menu;

@WebServlet("/menuModifyServlet")
public class MenuModifyServlet extends HttpServlet{

	private IMenuService ms = new MenuService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String menuId = req.getParameter("id"); 
				//System.out.println(stuId);
				Map<String, Object> mn = ms.findMenuInfo(Integer.valueOf(menuId));
				//System.out.println(st);
				req.setAttribute("menu", mn);
				//req.setAttribute("deptList", Department.values());
		req.getRequestDispatcher("manager/modifymenu.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String menuId = req.getParameter("id"); 
		String menuName = req.getParameter("menuName"); 
		String menuPrice = req.getParameter("menuPrice"); 
		String menuState = req.getParameter("menuState"); 
		Menu mn = new Menu();
		mn.setMenuId(Integer.valueOf(menuId));
		mn.setMenuName(menuName);
		mn.setMenuPrice(Integer.valueOf(menuPrice));
		mn.setMenuState(Integer.valueOf(menuState));
		ms.updateMenu(mn);
		req.getRequestDispatcher("/menuQueryServlet").forward(req, resp);
	}

}
