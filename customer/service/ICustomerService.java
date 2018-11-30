package com.onlineordering.customer.service;

import java.util.List;
import java.util.Map;

import com.onlineordering.po.Customer;

public interface ICustomerService {

	
	public List findCustomerInfo(String key);
	public Map<String, Object> findCustomerInfo(int cusId);
	public void addCustomer(Customer customer);
	public void deleteCustomer(String cusId);
	public void updateCustomer(Customer customer);
}
