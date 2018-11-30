package com.onlineordering.login.service;

import com.onlineordering.login.dao.ILoginDao;
import com.onlineordering.login.dao.LoginDao;
import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;

public class LoginService implements ILoginService{

	private ILoginDao ls = new LoginDao();
	@Override
	public Admin canLogin(Admin t) {
		// TODO Auto-generated method stub
		return ls.canLogin(t);
	}

	@Override
	public Customer canLogin(Customer t) {
		// TODO Auto-generated method stub
		return ls.canLogin(t);
	}

	public void AddCustomer(Customer customer){
		ls.AddCustomer(customer);
	}
	public void AddAdmin(Admin admin){
		ls.AddAdmin(admin);
	}
	
}
