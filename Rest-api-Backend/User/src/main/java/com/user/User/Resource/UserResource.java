package com.user.User.Resource;

import java.security.NoSuchAlgorithmException
;
import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
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
import com.user.User.Repository.UserRepository;
import com.user.User.model.User;
import com.user.responsebean.CustomerResponse;
import com.user.services.JwtTokenSerives;

@Path("acc")
public class UserResource {
	

	UserRepository repo = new UserRepository();
	JwtTokenSerives tokenSerives=new JwtTokenSerives();
	CustomerResponse res = new CustomerResponse();
	CustomerRepository cus = new CustomerRepository();
	
	@GET
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ArrayList<User> getuser() {
		
		
		   return repo.getUsers(); 
		
	}
	
	
	@GET
	@Path("get")
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ArrayList<User> getuser2() {
		
		
		   return repo.getUsers(); 
		
	}
	

	
	@GET
	@Path("a/{id}")
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User getuser(@PathParam("id")int id) {
		
		
		System.out.println("get one user called");
		   return repo.getOneUser(id); 
		
	}
	
	
	@POST
	@Path("regi")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) 
	public Response RegisterUser(User s1,@HeaderParam("Authcode")String token,@HeaderParam("uname")String name) {
		System.out.println("insert called");
		
		System.out.println(s1);
		boolean isAuth = tokenSerives.verifyToken(token, name);
		if(isAuth) {
			repo.create(s1);	
			return Response.status(200).entity("Success").build();
		}else {
			return Response.status(401).entity("Not Aoth").build();
		}
		
	}
	
	
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) 
	public User UpdateUser(User s1) {
		System.out.println("update user called");
		
		System.out.println(s1);
		repo.Update(s1);
		
		return s1;
	}
	 
	

	@DELETE
	@Path("delete/{id}")
	public Response DeleteUser(@PathParam("id")int id,@HeaderParam("Authcode")String token,@HeaderParam("uname")String name) {
		
		System.out.println("delete called");
		System.out.println("sucessfully  deleted");
		boolean isAuth = tokenSerives.verifyToken(token, name);
		if(isAuth) {
			User a = repo.getOneUser(id);
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
	public Response  secureMethod(User s1) {
		System.out.println("login sucss");
		System.out.println("sucessfuly loged");
		User data =  repo.login(s1);
		if(data!=null) {
			String token;
			try {
				
				
				token = tokenSerives.issueToken(data.getName());
				boolean found= tokenSerives.verifyToken(token,data.getName());
				data.setToken(token);
				System.out.println(found);
				
				System.out.println(res.getName());
				return Response.status(200).entity(data).build();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
		 return Response.status(200).entity("User Not Found.!").build();
	
	}
	
	
	@GET
	@Path("/Customers")
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ArrayList<Customer> getallRegisteredCustomer(){
		
		
		return cus.getAllCustomerRegisterd();
	}
	
	
	@GET
	@Path("cus/{id}")
	 @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getuser3(@HeaderParam("Authcode")String token,@HeaderParam("name")String name,@PathParam("id")int id,User s1) {
		
		
		
		   
		   boolean isAuth = tokenSerives.verifyToken(token, name);
			if(isAuth) {
	
				cus.CustomerProfile(id);
				return Response.status(200).entity(cus.CustomerProfile(id)).build();
				
			}else {
				return Response.status(401).entity("Not Aoth").build();
			}
		
	}
	
	@DELETE
	@Path("de/{id}")
	public Response DeleteUser1(@PathParam("id")int id,@HeaderParam("Authcode")String token,@HeaderParam("uname")String name) {
		
		System.out.println("delete called");
		System.out.println("sucessfully  deleted");
		boolean isAuth = tokenSerives.verifyToken(token, name);
		if(isAuth) {
			Customer a = cus.CustomerProfile(id);
			if(a.getId()!= 0)
				cus.Delete(id);
			return Response.status(200).entity("Success").build();
		}else {
			return Response.status(401).entity("Not Aoth").build();
		}
		
	}
	
	
	@DELETE
	@Path("de/{id}")
	public Response DeleteUserPayment(@PathParam("id")int id,@HeaderParam("Authcode")String token,@HeaderParam("uname")String name) {
		
		System.out.println("delete called");
		System.out.println("sucessfully  deleted");
		boolean isAuth = tokenSerives.verifyToken(token, name);
		if(isAuth) {
			Customer a = cus.CustomerProfile(id);
			if(a.getId()!= 0)
				cus.Delete(id);
			return Response.status(200).entity("Success").build();
		}else {
			return Response.status(401).entity("Not Aoth").build();
		}
		
	}
	
	
	 
}
