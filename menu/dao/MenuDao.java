package com.onlineordering.menu.dao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlineordering.po.Menu;
import com.onlineordering.util.DBUtil;

public class MenuDao implements IMenuDao{

	private DBUtil db = new DBUtil();
	
	public List findMenuInfo(int admId, String key) {
		List menuList = new ArrayList<Menu>();
		String val;
		try {
			if(key == null){
				val = null;
			}
			else val = new String(key.getBytes("ISO-8859-1"),"UTF-8");
			String sql = "select * from menu where admId=?";
			if(null != val && !"".equals(val)){
				sql += " and menu.menuName like '%"+val+"%'";
			}
			
				menuList = db.getQueryList(Menu.class, sql, new Object[]{admId});
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return menuList;
	}
	public void addMenu(Menu menu){
		String sql = "insert into menu(menuName,menuPrice,menuState,admId) value(?,?,?,?)";
		try {
			String name = new String(menu.getMenuName().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{name,menu.getMenuPrice(),menu.getMenuState(),menu.getAdmId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteMenu(String menuId) {
		// TODO Auto-generated method stub
		String sql = "delete from menu where menuId=?";
		try {
			db.execute(sql, new Object[]{menuId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> findMenuInfo(int menuId) {
		// TODO Auto-generated method stub
		String sql = "select * from menu where menuId=?";
		Map<String,Object> menuMap = new HashMap<String, Object>();
		
		try {
			menuMap = db.getObject(sql, new Object[]{menuId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
		menuMap = new HashMap<String, Object>();
			e.printStackTrace();
		}
		
		
		return menuMap;
	}
	
	public void updateMenu(Menu menu) {
		//System.out.println(s.getClaId());
		// TODO Auto-generated method stub
		String sql = "update menu set menuName=?,menuPrice=?,menuState=? where menuId=?";
		try {
			String name = new String(menu.getMenuName().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{name,menu.getMenuPrice(),menu.getMenuState(),menu.getMenuId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
