package com.onlineordering.admin.dao;

import java.util.Map;

import com.onlineordering.po.Admin;

public interface IAdminDao {

	public Map<String, Object> findAdminInfo(int admId);
	public void UpdateAdmin(Admin ad);
}
