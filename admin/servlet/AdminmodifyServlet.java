package com.onlineordering.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.admin.service.AdminService;
import com.onlineordering.admin.service.IAdminService;
import com.onlineordering.menu.service.IMenuService;
import com.onlineordering.menu.service.MenuService;
import com.onlineordering.po.Admin;

@WebServlet("/adminModifyServlet")
public class AdminmodifyServlet extends HttpServlet{

	
	private IAdminService as = new AdminService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String admName=req.getParameter("admName");
		String admAdr=req.getParameter("admAdr");
		String admPnum=req.getParameter("admPnum");
		String admPostage=req.getParameter("admPostage");
		String admTableware=req.getParameter("admTableware");
		String admPwd=req.getParameter("admPwd");
		String admState=req.getParameter("admState");
		Admin admin = (Admin) req.getSession().getAttribute("user");
		int admId = admin.getAdmId();
		Admin ad = new Admin();
		ad.setAdmId(admId);
		ad.setAdmName(admName);
		ad.setAdmAdr(admAdr);
		ad.setAdmPnum(admPnum);
	    ad.setAdmPostage(Integer.valueOf(admPostage));
		ad.setAdmTableware(Integer.valueOf(admTableware));
		ad.setAdmPwd(admPwd);
		ad.setAdmState(Integer.valueOf(admState));
		as.UpdateAdmin(ad);
		req.getSession().invalidate();
		req.getSession().setAttribute("user",ad);
		req.getRequestDispatcher("/manageQueryServlet").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		
	}
	
}
