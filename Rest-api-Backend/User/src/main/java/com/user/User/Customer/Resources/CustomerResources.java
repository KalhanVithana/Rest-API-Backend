package com.user.User.Customer.Resources;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.user.User.Customer.model.Customer;
import com.user.User.Customer.repository.CustomerRepository;
import com.user.User.model.User;
import com.user.db.DatabaseConnection;
import com.user.services.JwtTokenSerives;


@Path("cus")
public class CustomerResources {

	CustomerRepository repo = new CustomerRepository();
	JwtTokenSerives tokenSerives=new JwtTokenSerives();
	
	
	
	
	@GET
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ArrayList<Customer> getAllcustomer(){
		
		
		return repo.getAllCustomerRegisterd();
	}
	
	
	
	@GET
	@Path("a/{id}")
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Customer getuser(@PathParam("id")int id) {
		
		
		System.out.println("get one user called");
		   return repo.CustomerProfile(id);
		
	}
	
	
	@POST
	@Path("regi")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) 
	public Response RegisterUser(Customer c1,@HeaderParam("Authcode")String token,@HeaderParam("uname")String name) {
		System.out.println("insert called");
		
		System.out.println(c1);
		boolean isAuth = tokenSerives.verifyToken(token, name);
		if(isAuth) {
			repo.RegisterCustomer(c1);
			return Response.status(200).entity("Success").build();
		}else {
			return Response.status(401).entity("Not Aoth").build();
		}
		
	}
	
	
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) 
	public Customer UpdateUser(Customer c1) {
		System.out.println("update user called");
		
		System.out.println(c1);
		repo.Update(c1);
		
		return c1;
	}
	 
	

	@DELETE
	@Path("delete/{id}")
	public Response DeleteUser(@PathParam("id")int id,@HeaderParam("Authcode")String token,@HeaderParam("uname")String name) {
		
		System.out.println("delete called");
		System.out.println("sucessfully  deleted");
		boolean isAuth = tokenSerives.verifyToken(token, name);
		if(isAuth) {
			Customer a = repo.CustomerProfile(id);
			if(a.getId()!= 0)
				repo.Delete(id);
			return Response.status(200).entity("Success").build();
		}else {
			return Response.status(401).entity("Not Aoth").build();
		}
		
		
		
		
	}
	
	
	
	
	@POST
	@Path("log")
	@Produces({MediaType.APPLICATION_JSON})
	public Response  secureMethod(Customer s1) {
		DatabaseConnection.getConnection();
		System.out.println("login sucss");
		System.out.println("sucessfuly loged");
		Customer data =  repo.login(s1);
		if(data!=null) {
			String token;
			try {
				token = tokenSerives.issueToken(data.getName());
				boolean found= tokenSerives.verifyToken(token,data.getName());
				data.setToken(token);
				System.out.println(found);
				return Response.status(200).entity(data).build();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
		 return Response.status(200).entity("User Not Found.!").build();
	
	}
	
	 
}
