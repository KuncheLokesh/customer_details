package com.customer.test;

import com.customers.daoimpl.CustomerDaoImpl;

public class Test {

	public static void main(String[] args) {
		CustomerDaoImpl customerDaoImpl=new CustomerDaoImpl();
		System.out.println(customerDaoImpl.getCustomerById(6));
	}
	
}
