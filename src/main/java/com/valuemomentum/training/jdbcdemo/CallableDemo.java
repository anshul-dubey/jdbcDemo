package com.valuemomentum.training.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.CallableStatement;

public class CallableDemo {
	
	Connection con;
	java.sql.CallableStatement cstmt;	
	ResultSet rs;
	
	CallableDemo()
	{
	
	}
	
	void createConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqljdbc", "root", "Anshulonmaria");
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void getSkilss(int candidateId)
	{
		try
		{
			String query="{ Call get_candidate_skill(?) }";
			cstmt=con.prepareCall(query);
			cstmt.setInt(1,candidateId);
			
			rs=cstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(String.format("%s - %s", rs.getString("first_name")+
				" " +rs.getString("last_name"), rs.getString("skill")));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		CallableDemo cd1=new CallableDemo();
		cd1.createConnection();
		cd1.getSkilss(133);
		
		

	}

}
