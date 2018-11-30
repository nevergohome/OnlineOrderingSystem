package com.onlineordering.customer.service;

import java.util.List;
import java.util.Map;

import com.onlineordering.customer.dao.CustomerDao;
import com.onlineordering.customer.dao.ICustomerDao;
import com.onlineordering.po.Customer;

public class CustomerService implements ICustomerService{
	private ICustomerDao it = new CustomerDao();
	

	public List findCustomerInfo(String key){
		return it.findCustomerInfo(key);
	}
	public Map<String, Object> findCustomerInfo(int cusId){
		return it.findCustomerInfo(cusId);
	}
	public void addCustomer(Customer customer){
		it.addCustomer(customer);
	}
	public void deleteCustomer(String cusId){
		it.deleteCustomer(cusId);
	}
	public void updateCustomer(Customer customer){
		it.updateCustomer(customer);
	}
}
