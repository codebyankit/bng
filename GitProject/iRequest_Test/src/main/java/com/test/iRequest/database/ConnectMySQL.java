package com.test.iRequest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class ConnectMySQL {


		// TODO Auto-generated method stub
		@Test
		public void testDB() throws ClassNotFoundException, SQLException {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/irequesttest","root","pass@123");
			System.out.println("Connected to my sql DB");
		Statement smt	=con.createStatement();
		ResultSet rs = smt.executeQuery("select * from irequesttest.Testdata");

		while (rs.next()) {
			String firstURL=rs.getString("URL");
			System.out.println("database Record is:" +firstURL);
	
//			String Value1=rs.getString("URL");
//			System.out.println("database Record is:" +Value1);
		
		}
		}

	}

