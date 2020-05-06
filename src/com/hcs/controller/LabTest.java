package com.hcs.controller;

import java.sql.*;

public class LabTest {

	private Connection connect()
	{
		Connection con = null;
		
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hcs", "root", "");
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}

	public String readLabTest()
	{
		String output = "";
	
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>PatientID</th>       "
					+ "<th>TestName</th>"
					+ "<th>TestType</th>"
					+ "<th>TestDescription</th>"
					+ "<th>LabDate</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";

			String query = "select * from labtest";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			
			while (rs.next())
			{
				String LabTestID = Integer.toString(rs.getInt("LabTestID"));
				String PatientID = Integer.toString(rs.getInt("PatientID"));
				String TestName = rs.getString("TestName");
				String TestType = rs.getString("TestType");
				String TestDescription = rs.getString("TestDescription");
				String LabDate = rs.getString("LabDate");  
				
				// Add into the html table
				output += "<tr><td><input id='hidLabTestIDUpdate'"
						+ "name='hidLabTestIDUpdate'"
						+ "type='hidden' value='" + LabTestID
						+ "'>"  +PatientID + "</td>";
				
			//	output += "<td>" + LabTestID + "</td>";
			//	output += "<td>" + PatientID + "</td>";
				
				output += "<td>" + TestName + "</td>";
				output += "<td>" + TestType + "</td>";
				output += "<td>" + TestDescription + "</td>";
				output += "<td>" + LabDate + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' "
						+ "type='button' value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' "
						+ "type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger'"
						+ "data-LabTestID='"
						+ LabTestID + "'>" + "</td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			
			 output += "</table>"; 
			
		}
		
		catch (Exception e)
		{
			output = "Error while reading the LabTest.";
			System.err.println(e.getMessage());
		}

		return output;
		
	}
	
	
	public String insertLabTest(String PatientID, String TestName,String TestType, String TestDescription, String LabDate)
	{
			String output = "";
			try
			{
				Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting LabTest.";
			}
			
			// create a prepared statement
			
			String query = " insert into labtest (`LabTestID`,`PatientID`,`TestName`,`TestType`,`TestDescription`,`LabDate`)"
			+ " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2,Integer.parseInt(PatientID));
			preparedStmt.setString(3, TestName);
			preparedStmt.setString(4, TestType);
			preparedStmt.setString(5, TestDescription);
			preparedStmt.setString(6, LabDate);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newLabTest = readLabTest();
			output = "{\"status\":\"success\", \"data\": \"" +newLabTest + "\"}";
			}
			
			catch (Exception e)
			{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the LabTests.\"}";
			System.err.println(e.getMessage());
			}
			
			return output;
	}
	
	
	public String updateLabTest(String LabTestID, String PatientID, String TestName,String TestType, String TestDescription,String LabDate)
	{
			String output = "";
			
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating LabTest.";
				}
			
				// create a prepared statement
			
				String query = "UPDATE labtest SET PatientID=?,TestName=?,TestType=?,TestDescription=?,LabDate=? WHERE LabTestID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding values
			
				preparedStmt.setString(1,PatientID);
				preparedStmt.setString(2, TestName);
				preparedStmt.setString(3, TestType);
				preparedStmt.setString(4, TestDescription);
				preparedStmt.setString(5, LabDate);
				preparedStmt.setInt(6, Integer.parseInt(LabTestID));
		
				// execute the statement
			
				preparedStmt.execute();
				con.close();
			
				String newLabTest = readLabTest();
				output = "{\"status\":\"success\", \"data\": \"" +newLabTest + "\"}";
			}
			
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the LabTest.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
			}
				
	public String deleteLabTest(String LabTestID)
	{
			
			String output = "";
			try
			{
				Connection con = connect();
			
				if (con == null)
				{
					return "Error while connecting to the database for deleting LabTest.";
				}
			
				// create a prepared statement
		
				String query = "delete from labtest where LabTestID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
			
				// binding values
			
				preparedStmt.setInt(1, Integer.parseInt(LabTestID));
			
				// execute the statement
			
					preparedStmt.execute();
					con.close();
			
				String newLabTest = readLabTest();
				output = "{\"status\":\"success\", \"data\": \"" +newLabTest + "\"}";
			}
			
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":	\"Error while deleting the LabTest.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
	}
		
}
