package com.onlineordering.order.dao;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;
import com.onlineordering.po.Menu;
import com.onlineordering.po.Ordering;
import com.onlineordering.util.DBUtil;
import com.onlineordering.util.ToolUtil;


public class OrderDao implements IOrderDao {
	
	private DBUtil db = new DBUtil();
	public List<Map<String, Object>> findAll(int admId,int state){
		String sql = "select ordering.*, customer.*, admin.* from ordering,customer,admin where ordering.admId = admin.admId and ordering.cusId = customer.cusId and admin.admId = ? and ordering.orderState=?";
		List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
		try {
			orderList = db.getQueryList(sql,new Object[]{admId, state});
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	public void manageOrder(int orderId, int st){
		String sql = "update ordering set orderState=? where orderId=?";
		try {
			db.execute(sql, new Object[]{st,orderId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> findStatis(int admId,Timestamp startDate,Timestamp endDate){
		
		String sql = "SELECT * FROM ordering where admId=? and orderTime between ? and ? and orderState=3";
		List<Map<String, Object>> statisList = new ArrayList<Map<String, Object>>();
		try {
			statisList = db.getQueryList(sql,new Object[]{admId, startDate, endDate});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statisList;
	}
	
	public Map<String, Object> sumOrder(int admId,Timestamp startDate,Timestamp endDate){
		String sql = "SELECT COUNT(orderId) as sumOrder,SUM(orderTotal) AS sumMoney FROM `ordering` where admId=? and orderState=3 and orderTime between ? and ?";
		Map<String,Object> ordersumMap = new HashMap<String, Object>();
		
		try {
			ordersumMap = db.getObject(sql, new Object[]{admId, startDate, endDate});
		} catch (Exception e) {
			// TODO Auto-generated catch block
		ordersumMap = new HashMap<String, Object>();
			e.printStackTrace();
		}
		
		
		return ordersumMap;
	}
	
	public Map<String, Object> findorderInfor(int orderId){
		String sql = "select admin.admPostage,admin.admTableware,admin.admAdr,customer.cusName,customer.cusAdr,customer.cusPnum,ordering.ordertotal,ordering.remark,ordering.menuNum from admin,customer,ordering where admin.admId=ordering.admId and ordering.cusId=customer.cusId and ordering.orderId=?";
		Map<String,Object> orderInforMap = new HashMap<String, Object>();
		
		try {
			orderInforMap = db.getObject(sql, new Object[]{orderId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
		orderInforMap = new HashMap<String, Object>();
			e.printStackTrace();
		}
		return orderInforMap;
	}

	public List<Map<String, Object>> findNum(int orderId){
		String sql = "select a.orderId,substring_index(substring_index(a.menuNum,',',b.help_topic_id+1),',', -1) as num from ordering a join mysql.help_topic b on b.help_topic_id < (length(a.menuNum) - length(replace(a.menuNum,',',''))+1) and a.orderId=? order by a.orderId";
		List<Map<String, Object>> ordernumList = new ArrayList<Map<String, Object>>();
		
		try {
			ordernumList = db.getQueryList(sql,new Object[]{orderId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ordernumList;
	}
	public List<Map<String, Object>> findMenuName(int orderId){
		String sql = "select menu.menuName,menu.menuPrice from menu,ordering where FIND_IN_SET(menu.menuId,ordering.menuIds) and ordering.orderId=?";
		List<Map<String, Object>> ordermenuList = new ArrayList<Map<String, Object>>();
		
		try {
			ordermenuList = db.getQueryList(sql,new Object[]{orderId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ordermenuList;
	}

	@Override
	public List<Map<String, Object>> findAllByCusId(int cusId) {
		// TODO Auto-generated method stub
		String sql = "select ordering.*, customer.*, admin.* from ordering,customer,admin where ordering.admId = admin.admId and ordering.cusId = customer.cusId and customer.cusId = ? order by ordering.orderTime desc";
		List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
		try {
			orderList = db.getQueryList(sql,new Object[]{cusId});
			//System.out.println("123");
			//System.out.println(couList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Map<String, Object>> findAdminInfo(String key) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> adminList = new ArrayList<Map<String, Object>>();
		String val;
		try {
			if(key == null){
				val = null;
			}
			else val = new String(key.getBytes("ISO-8859-1"),"UTF-8");
			String sql = "select * from admin";
			if(null != val && !"".equals(val)){
				sql += " where admin.admAdr like '%"+val+"%'";
			}
			
				adminList = db.getQueryList(sql, new Object[]{});
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return adminList;
	}
	
	public List<Map<String, Object>> findMenuByadmId(int admId, String key){
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
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
			
				menuList = db.getQueryList(sql, new Object[]{admId});
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return menuList;
	}
	
	public List<Map<String, Object>> findMenuIds(String numids){
		String sql = "select menuId,menuName,menuPrice from menu where FIND_IN_SET(menu.menuId, ?)";
		List<Map<String, Object>> menuidList = new ArrayList<Map<String, Object>>();
		try {
			menuidList = db.getQueryList(sql,new Object[]{numids});
			//System.out.println("123");
			//System.out.println(couList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuidList;
	}
	public Map<String, Object> findOrderInfor(Customer cus, String admid){
		String sql = "select admin.*, customer.* from customer,admin where admin.admId=? and cusId=?;";
		Map<String,Object> orderInforMap = new HashMap<String, Object>();
		
		try {
			orderInforMap = db.getObject(sql, new Object[]{admid, cus.getCusId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
		orderInforMap = new HashMap<String, Object>();
			e.printStackTrace();
		}
		return orderInforMap;
	}
	public void createOrder(Ordering o){
		String sql = "insert into Ordering(admId,cusId,remark,menuIds,menuPrices,menuNum,orderTotal,orderTime,orderState) value(?,?,?,?,?,?,?,?,1)";
		try {
			String remark = new String(o.getRemark().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{o.getAdmId(),o.getCusId(),remark,o.getMenuIds(),o.getMenuPrices(),o.getMenuNum(),o.getOrderTotal(),ToolUtil.getCurrentTime()});
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
