package com.onlineordering.customer.dao;

import java.util.List;
import java.util.Map;

import com.onlineordering.po.Customer;
import com.onlineordering.po.Menu;

public interface ICustomerDao {

	public List findCustomerInfo(String key);
	public Map<String, Object> findCustomerInfo(int cusId);
	public void addCustomer(Customer customer);
	public void deleteCustomer(String cusId);
	public void updateCustomer(Customer customer);
}
