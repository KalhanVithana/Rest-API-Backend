package com.user.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.jvnet.hk2.annotations.Service;

import com.user.responsebean.CustomerResponse;

@Service
public class CustomerServices {
	
	public CustomerResponse getCutomerData(String custId) {
		Client client = ClientBuilder.newClient();
		CustomerResponse data =  client.target("http://localhost:8082/User/webapi/cus/log"+custId)
		.request(MediaType.APPLICATION_JSON)
		.get(CustomerResponse.class);
		return data;
	}

	/*
	 * 	Client client = ClientBuilder.newClient();
		CustomerResponse data =  client.target("http://<other-service-address>:<port>/rest/api/"+custId)
		.request(MediaType.APPLICATION_JSON)
		.get(CustomerResponse.class);
	 */
}
