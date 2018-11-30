package com.onlineordering.menu.dao;

import java.util.List;
import java.util.Map;

import com.onlineordering.po.Menu;


public interface IMenuDao {
	
	

	public List findMenuInfo(int menuId, String key);
	public Map<String, Object> findMenuInfo(int menuId);
	public void addMenu(Menu menu);
	public void deleteMenu(String menuId);
	public void updateMenu(Menu menu);
	
}
