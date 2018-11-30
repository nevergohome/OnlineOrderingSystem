package com.onlineordering.customer.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlineordering.po.Customer;
import com.onlineordering.po.Menu;
import com.onlineordering.util.DBUtil;

public class CustomerDao implements ICustomerDao{

	private DBUtil db = new DBUtil();
	public List findCustomerInfo(String key){
		List cusList = new ArrayList<Customer>();
		String val;
		try {
			if(key == null){
				val = null;
			}
			else val = new String(key.getBytes("ISO-8859-1"),"UTF-8");
			String sql = "select * from customer";
			if(null != val && !"".equals(val)){
				sql += " where customer.cusName like '%"+val+"%'";
			}
			
				cusList = db.getQueryList(Customer.class, sql, new Object[]{});
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return cusList;
	}
	@Override
	public Map<String, Object> findCustomerInfo(int cusId) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where cusId=?";
		Map<String,Object> cusMap = new HashMap<String, Object>();
		
		try {
			cusMap = db.getObject(sql, new Object[]{cusId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
		cusMap = new HashMap<String, Object>();
			e.printStackTrace();
		}
		
		
		return cusMap;
	}
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "insert into customer(cusName,cusPwd,cusAdr,cusPnum) value(?,?,?,?)";
		try {
			String name = new String(customer.getCusName().getBytes("ISO-8859-1"),"UTF-8");
			String pwd = new String(customer.getCusPwd().getBytes("ISO-8859-1"),"UTF-8");
			String adr = new String(customer.getCusAdr().getBytes("ISO-8859-1"),"UTF-8");
			String pnum = new String(customer.getCusPnum().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{name,pwd,adr,pnum});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void deleteCustomer(String cusId) {
		// TODO Auto-generated method stub
		String sql = "delete from customer where cusId=?";
		try {
			db.execute(sql, new Object[]{cusId});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql = "update customer set cusName=?,cusPwd=?,cusAdr=?,cusPnum=? where cusId=?";
		try {
			//System.out.println(s.getClaId());
			String name = new String(customer.getCusName().getBytes("ISO-8859-1"),"UTF-8");
			String pwd = new String(customer.getCusPwd().getBytes("ISO-8859-1"),"UTF-8");
			String adr = new String(customer.getCusAdr().getBytes("ISO-8859-1"),"UTF-8");
			String pnum = new String(customer.getCusPnum().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{name,pwd,adr,pnum,customer.getCusId()});
			//System.out.println(s.getClaId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
