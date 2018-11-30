package com.onlineordering.order.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.onlineordering.po.Customer;
import com.onlineordering.po.Ordering;

public interface IOrderService {
	
	public List<Map<String, Object>> findAll(int admId,int state);
	public void manageOrder(int orderId,int st);
	public List<Map<String, Object>> findStatis(int admId,Timestamp startDate,Timestamp endDate);
	public Map<String, Object> sumOrder(int admId,Timestamp startDate,Timestamp endDate);
	public Map<String, Object> findorderInfor(int orderId);
	public List<Map<String, Object>> findNum(int orderId);
	public List<Map<String, Object>> findMenuName(int orderId);
	public List<Map<String, Object>> findAllByCusId(int cusId);
	public List<Map<String, Object>> findAdminInfo(String key);
	public List<Map<String, Object>> findMenuByadmId(int admId, String key);
	public List<Map<String, Object>> findMenuIds(String numids);
	public Map<String, Object> findOrderInfor(Customer cus, String admid);
	public void createOrder(Ordering o);

}
