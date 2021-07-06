package com.user.User.Customer.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.user.User.Customer.model.Customer;
import com.user.User.model.User;
import com.user.db.CustomerDatabaseConnection;

public class CustomerRepository {

	
	

	Connection con = null;
	
	  
	 public CustomerRepository() {
		 
		 con = CustomerDatabaseConnection.getConnection();		 
		 
	
		 
	 }
	 
 public ArrayList<Customer> getAllCustomerRegisterd(){
		 
		 
		 ArrayList<Customer> Customer = new  ArrayList<Customer>();
		 String sql = "select * from customer";
		 try {
		 java.sql.Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while(rs.next()) {
			 
			 Customer a = new Customer();
			
			 
			 
			 a.setId(rs.getInt(1));
			 a.setName(rs.getString(2));
			 a.setEmail(rs.getString(3));
			 a.setMobile(rs.getString(4));
			 a.setNic(rs.getString(4));
			 a.setPassword(rs.getString(5));

			 Customer.add(a);
		 }
		 
		 }
		 catch(Exception e) {
			 
			 System.out.println("get user error");
			 e.printStackTrace();
		 }
		 
		 return Customer;
	 }
	 
	 
		public void RegisterCustomer(Customer c1) {
			// TODO Auto-generated method stub

			String sql = "insert into customer values(?,?,?,?,?,?)";

			try {
				PreparedStatement st = con.prepareStatement(sql);

				st.setInt(1, c1.getId());
				st.setString(2, c1.getName());
				st.setString(3, c1.getEmail());
				st.setString(4, c1.getMobile());
				st.setString(5, c1.getNic());
				st.setString(6, c1.getPassword());

				st.executeUpdate();

			} catch (Exception e) {

				System.out.println("user cant create  ");
				Response.status(404);
				e.printStackTrace();
			}

		}
	 
	 
	 public Customer CustomerProfile(int id) {
		 
			
		 String sql = "select * from customer where id="+id;
		 Customer a = new Customer(); 
		 try {
		 java.sql.Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 if(rs.next()) {
			 
			
			 a.setId(rs.getInt(1));
			 a.setName(rs.getString(2));
			 a.setEmail(rs.getString(3));
			 a.setMobile(rs.getString(4));
			 a.setNic(rs.getString(5));
			 a.setPassword(rs.getString(6));
			
		
			 
			
		 }
		 
		 }
		 catch(Exception e) {
			 
			 System.out.println("get one user eerror");
			 e.printStackTrace();
		 }
		 return a;
	 }
	 
	 

		public void  Update(Customer s1) {
			// TODO Auto-generated method stub
			
		 
			String sql = "update customer set name=?,email =?,mobile=?,password=?,nic=? where id=?";
			
			 try {
				 PreparedStatement st = con.prepareStatement(sql);
				
				
					 
				
					 
					
					 st.setString(1, s1.getName());
					 st.setString(2, s1.getEmail());
					 st.setString(3, s1.getMobile());
					 st.setString(4, s1.getNic());
					 st.setString(5, s1.getPassword());
					 st.setInt(6,s1.getId());
					 
					 st.executeUpdate();
				 
				 
				 }
				 catch(Exception e) {
					 
					 System.out.println("user not updated");
					 Response.status(404);
					 e.printStackTrace();
				 }
				
			
		}
		
		
		public void  Delete(int id) {
			// TODO Auto-generated method stub
			
		 
			String sql = "delete from customer where id=?";
			
			 try {
				 PreparedStatement st = con.prepareStatement(sql);
				
					
					 st.setInt(1,id);
					 
					 st.executeUpdate();
				 
				 
				 }
				 catch(Exception e) {
					 
					 System.out.println("error");
					 Response.status(404);
					 e.printStackTrace();
				 }
				
			
		}
		 
		
		public Customer login(Customer c1) {
			
			
			String sql = "select name,password from customer where name = ? AND password =?";
			
			try {
				
				

				 PreparedStatement st = con.prepareStatement(sql);
				 
				 st.setString(1, c1.getName());
				 st.setString(2, c1.getPassword());
				 ResultSet resul = st.executeQuery();
				
		         if(resul.next()){
		        	 return new Customer(resul.getString("name"), resul.getString("password"));
		         }
		         
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
			return null;
	     } 
			

	 
}
