package com.onlineordering.menu.service;

import java.util.List;
import java.util.Map;

import com.onlineordering.menu.dao.IMenuDao;
import com.onlineordering.menu.dao.MenuDao;
import com.onlineordering.po.Menu;



public class MenuService implements IMenuService{
	 private IMenuDao md = new MenuDao();
	 
	 
	public List findMenuInfo(int menuId, String key)
	{
		return md.findMenuInfo(menuId, key);
	}
	public Map<String, Object> findMenuInfo(int menuId){
		return md.findMenuInfo(menuId);
	}
	
	public void addMenu(Menu menu){
		md.addMenu(menu);
	}
	public void deleteMenu(String menuId){
		md.deleteMenu(menuId);
	}
	public void updateMenu(Menu menu){
		md.updateMenu(menu);
	}
}
