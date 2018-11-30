package com.onlineordering.login.service;

import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;

public interface ILoginService {
	public Admin canLogin(Admin a);
	public Customer canLogin(Customer t);
	public void AddCustomer(Customer customer);
	public void AddAdmin(Admin admin);
}
