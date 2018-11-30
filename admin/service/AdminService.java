package com.onlineordering.admin.service;

import java.util.Map;

import com.onlineordering.admin.dao.AdminDao;
import com.onlineordering.admin.dao.IAdminDao;
import com.onlineordering.po.Admin;

public class AdminService implements IAdminService{

	
	IAdminDao ad = new AdminDao();
	public Map<String, Object> findAdminInfo(int admId){
		return ad.findAdminInfo(admId);
	}
	public void UpdateAdmin(Admin admin){
		ad.UpdateAdmin(admin);
	}
}
