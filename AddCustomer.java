package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class AddCustomer {

		Connection con;
		PreparedStatement pre;
		Statement stat;
		ResultSet res;
		int id;
		String fname;
		String lname;
		String address;
		String email;
		public AddCustomer()

		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
				System.out.println("Database Connected....");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void insert()

		{
			try
			{
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter customer id: ");
				id=sc.nextInt();
				System.out.println("Enter customer first name: ");
				fname=sc.next();
				System.out.println("Enter customer last name: ");
				lname=sc.next();
				System.out.println("Enter customer address: ");
				address=sc.next();
				System.out.println("Enter customer email: ");
				email=sc.next();
				pre=con.prepareStatement("insert into customer values(?,?,?,?,?)");
				pre.setInt(1, id);
				pre.setString(2,fname);
				pre.setString(3,lname);
				pre.setString(4,address);
				pre.setString(5,email);
				
				int ra=pre.executeUpdate();
				if(ra>0)
					System.out.println("Customer Details Committed..");
				else
					System.out.println("Customer Details are Not Committed..");
				pre.close();
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void printCustomerDetails()
		{
			try
			{
				stat=con.createStatement();
				res=stat.executeQuery("select * from customer");
				while(res.next())
				{
					System.out.println("id : "+res.getInt("id"));
					System.out.println("First Name : "+res.getString("fname"));
					System.out.println("Last Name : "+res.getString("lname"));
					System.out.println("Address : "+res.getString("address"));
					System.out.println("Email : "+res.getString("email"));
					System.out.println("");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		  public void updateCustomerDetails() { try {
			  
		  pre=con.prepareStatement("update customer set fname=? where id=?");
		  pre.setString(1, "ashu"); pre.setInt(2,1); int ra=pre.executeUpdate();
		  if(ra>0) System.out.println("Name updated"); else
		  System.out.println("Name is not updated"); pre.close(); } catch(Exception
		  e) { e.printStackTrace(); } }
		 

		
		  public void deleteCustomerDetails() { try {
		  pre=con.prepareStatement("delete from customer where id=?");
		  pre.setInt(1,2);
		  
		  int ra=pre.executeUpdate(); if(ra>0) System.out.println("Record Deleted");
		  else System.out.println("Record has not been Deleted");
		  
		  } catch(Exception e) { e.printStackTrace(); } }
		  
		 
		public static void main(String[] args) {
			
			boolean l=true;
			
			AddCustomer obj=new AddCustomer();
			
			while(l==true)
			{
			
			System.out.println("1:insert ...... 2:update ..... 3:remove ......4:print ..... 5:exit");
			Scanner sc=new Scanner(System.in);
			int ch=sc.nextInt();
			
			if(ch==1)
			{
				obj.insert();
			}
			else if(ch==2)
			{
				obj.updateCustomerDetails();
			}
			else if(ch==3)
			{
				obj.deleteCustomerDetails();	
			}
			else if(ch==4)
			{
			    obj.printCustomerDetails();
			}
			else
			{
				l=false;
				break;
			}
			
			}
			
			
		}

	}

