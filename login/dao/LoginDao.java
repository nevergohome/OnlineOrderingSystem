package com.onlineordering.login.dao;

import com.onlineordering.po.Admin;
import com.onlineordering.po.Customer;
import com.onlineordering.util.DBUtil;

public class LoginDao implements ILoginDao{
	
	private DBUtil db = new DBUtil();

	@Override
	public Admin canLogin(Admin t) {
		// TODO Auto-generated method stub
		//System.out.println(t.getAdmId()+"and"+t.getAdmPwd());
		String sql = "select * from admin where admId = ? and admPwd = ?";
		Admin t2 = new Admin();
		try {
			t2 = (Admin) db.getObject(Admin.class, sql, new Object[] {t.getAdmId(),t.getAdmPwd()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(t2.getTeaName()+"and"+t2.getTeaPwd());
		//System.out.println(t2.getAdmName()+" "+t2.getAdmId()+" "+t2.getAdmPwd());
		return t2;

	}

	@Override
	public Customer canLogin(Customer s) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where cusId = ? and cusPwd = ?";
		Customer stu = new Customer();
		try {
			stu = (Customer)db.getObject(Customer.class, sql, new Object[]{s.getCusId(),s.getCusPwd()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}

	public void AddCustomer(Customer customer){
		String sql = "insert into customer(cusId,cusName,cusPwd,cusPnum,cusAdr) value(?,?,?,?,?)";
		try {
			String name = new String(customer.getCusName().getBytes("ISO-8859-1"),"UTF-8");
			String password = new String(customer.getCusPwd().getBytes("ISO-8859-1"),"UTF-8");
			String phone = new String(customer.getCusPnum().getBytes("ISO-8859-1"),"UTF-8");
			String adress = new String(customer.getCusAdr().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{customer.getCusId(),name,password,phone,adress});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void AddAdmin(Admin admin){
		String sql = "insert into admin(admId,admName,admPwd,admPnum,admAdr,admState,admPostage,admTableware) value(?,?,?,?,?,?,?,?)";
		try {
			String name = new String(admin.getAdmName().getBytes("ISO-8859-1"),"UTF-8");
			String password = new String(admin.getAdmPwd().getBytes("ISO-8859-1"),"UTF-8");
			String phone = new String(admin.getAdmPnum().getBytes("ISO-8859-1"),"UTF-8");
			String adress = new String(admin.getAdmAdr().getBytes("ISO-8859-1"),"UTF-8");
			db.execute(sql, new Object[]{admin.getAdmId(),name,password,phone,adress,admin.getAdmState(),admin.getAdmPostage(),admin.getAdmTableware()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
