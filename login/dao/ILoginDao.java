package com.onlineordering.login.dao;

import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;

public interface ILoginDao {


	public Admin canLogin(Admin t);
	
	public Customer canLogin(Customer s);
	
	public void AddCustomer(Customer customer);
	
	public void AddAdmin(Admin admin);
}

