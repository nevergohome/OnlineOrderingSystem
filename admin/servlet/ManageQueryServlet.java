package com.onlineordering.admin.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onlineordering.admin.service.AdminService;
import com.onlineordering.admin.service.IAdminService;
import com.onlineordering.po.Admin;

@WebServlet("/manageQueryServlet")
public class ManageQueryServlet extends HttpServlet {

	
	IAdminService as = new AdminService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin ad = (Admin) req.getSession().getAttribute("user");
		Map<String, Object> adminMap = as.findAdminInfo(ad.getAdmId());
		req.setAttribute("adminMap", adminMap);
		req.getRequestDispatcher("/manager/admininfor.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin ad = (Admin) req.getSession().getAttribute("user");
		Map<String, Object> adminMap = as.findAdminInfo(ad.getAdmId());
		req.setAttribute("adminMap", adminMap);
		req.getRequestDispatcher("/manager/adminmodifyinfor.jsp").forward(req, resp);
	}

	
	
	
}
