package com.sravan.impl;

import java.util.Hashtable;

import com.sravan.Service.TClass;

public class ServiceClass extends TClass {

	public void doGetMethod(StringBuffer response,
			Hashtable<String, String> request) {
		try {
			response.append("hi You Have Sent Name " + request.get("name")
					+ " Message Is: " + request.get("message"));
		//	System.out.println(response);
		} catch (Exception e) {
			//System.out.println(e);
		}

	}
}
