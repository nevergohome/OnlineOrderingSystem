package com.onlineordering.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlineordering.po.Admin;
import com.onlineordering.util.DBUtil;

public class AdminDao implements IAdminDao{

	
	private DBUtil db = new DBUtil();
	public Map<String, Object> findAdminInfo(int admId){
		String sql = "select * from admin where admId=?";
		Map<String,Object> adminMap = new HashMap<String, Object>();
		
		try {
			adminMap = db.getObject(sql, new Object[]{admId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
	        adminMap = new HashMap<String, Object>();
			e.printStackTrace();
		}
		
		
		return adminMap;
	}
	public void UpdateAdmin(Admin ad){
		String sql = "update admin set admName=?,admAdr=?,admPnum=?,admPostage=?,admTableware=?,admPwd=?,admState=? where admId=?";
		try {
			String name = new String(ad.getAdmName().getBytes("ISO-8859-1"),"UTF-8");
			String adress = new String(ad.getAdmAdr().getBytes("ISO-8859-1"),"UTF-8");
			String pnum = new String(ad.getAdmPnum().getBytes("ISO-8859-1"),"UTF-8");
			String password = new String(ad.getAdmPwd().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{name,adress,pnum,ad.getAdmPostage(),ad.getAdmTableware(),password,ad.getAdmState(),ad.getAdmId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
