package com.consume.main;

import com.consume.method.CustomerConsume;
import com.consume.method.FriendConsume;
import com.consume.model.Customer;
import com.consume.model.Friend;

public class MainClass {

	public static void main(String[] args) {
		CustomerConsume custom = new CustomerConsume();

		Customer cust = new Customer();
		cust.setFirstName("Bang");
		cust.setLastName("Ozil");
		cust.setEmail("Bang.ozil@gmail.com");
		cust.setMobile("082240255732");
		System.out.println("");

		/* To Do */
		// custom.updateUser(cust,9)s;
//		custom.getCustomer(9);
		 updateFriend();
	}

	public static void updateFriend() {
		FriendConsume f = new FriendConsume();
		System.out.println("Start Calling");
		Friend friend = new Friend();
		friend.setFirstName("Ridwan");
		friend.setLastName("Kamil");
		System.out.println("End Calling");
		
//		f.createFriend(friend);
//		f.updateFriend(friend, "402881906694de35016694de38230000");
	}
}
