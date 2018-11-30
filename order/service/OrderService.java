package com.onlineordering.order.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.onlineordering.order.dao.IOrderDao;
import com.onlineordering.order.dao.OrderDao;
import com.onlineordering.po.Customer;
import com.onlineordering.po.Ordering;
import com.onlineordering.util.DBUtil;

public class OrderService implements IOrderService{
	
	IOrderDao od = new OrderDao();
	
	public List<Map<String, Object>> findAll(int admId, int state){
		return od.findAll(admId,state);
	}

	public void manageOrder(int orderId,int st){
		od.manageOrder(orderId, st);
	}
	
	public List<Map<String, Object>> findStatis(int admId,Timestamp startDate,Timestamp endDate){
	    return od.findStatis(admId, startDate, endDate);
	}
	public Map<String, Object> sumOrder(int admId,Timestamp startDate,Timestamp endDate){
		return od.sumOrder(admId, startDate, endDate);
	}
	public Map<String, Object> findorderInfor(int orderId){
		return od.findorderInfor(orderId);
	}
	public List<Map<String, Object>> findNum(int orderId){
		return od.findNum(orderId);
	}
	public List<Map<String, Object>> findMenuName(int orderId){
		return od.findMenuName(orderId);
	}
	public List<Map<String, Object>> findAllByCusId(int cusId){
		return od.findAllByCusId(cusId);
	}

	@Override
	public List<Map<String, Object>> findAdminInfo(String key) {
		// TODO Auto-generated method stub
		return od.findAdminInfo(key);
	}
	public List<Map<String, Object>> findMenuByadmId(int admId, String key){
		return od.findMenuByadmId(admId, key);
	}
	public List<Map<String, Object>> findMenuIds(String numids){
		return od.findMenuIds(numids);
	}
	public Map<String, Object> findOrderInfor(Customer cus, String admid){
		return od.findOrderInfor(cus, admid);
	}
	public void createOrder(Ordering o){
		od.createOrder(o);
	}

}
