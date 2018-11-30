package com.onlineordering.admin.service;

import java.util.Map;

import com.onlineordering.admin.dao.AdminDao;
import com.onlineordering.admin.dao.IAdminDao;
import com.onlineordering.po.Admin;

public interface IAdminService {

	public Map<String, Object> findAdminInfo(int admId);
	public void UpdateAdmin(Admin ad);
}
