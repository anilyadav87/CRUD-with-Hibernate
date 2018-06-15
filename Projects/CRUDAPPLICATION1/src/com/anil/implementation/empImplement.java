package com.anil.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.anil.config.*;
import com.anil.model.*;

import dao.Iemployee;
public class empImplement implements Iemployee
{

	PreparedStatement preparedStatement;
	Statement statement;
	ResultSet resultSet;
	Connection connection;
	
	//constructor
	public empImplement() 
	{
		connection = Config.getConnection();
	}
	
	@Override
	public void insertRecord(EmployeeModel employeeModel) 
	{
		String str = "Insert into Employees(id,firstName,lastName,Address,contactNo) values (?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(str);
			preparedStatement.setInt(1, employeeModel.geteId());
			preparedStatement.setString(2, employeeModel.getFirstName());
			preparedStatement.setString(3, employeeModel.getLastName());
			preparedStatement.setString(4, employeeModel.getAddress());
			preparedStatement.setString(5, employeeModel.getContactNo());
			
			int rowInserted = preparedStatement.executeUpdate();
			if(rowInserted>0)
			{
				System.out.println("Record Inserted sucessfully");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	@Override
	public void deleteRecord(int eId) {
		//String str = "Update Employees Set firstName=?,lastName=?,Address=?,contactNo=? where eId=?";
		try {
			String query = "delete from Employees where id =" + eId;
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("user deleted Successfully");
	}

	@Override
	public List<EmployeeModel> employeeList() {
		List<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();

		String sql = "SELECT * FROM Employees";

		try {
			EmployeeModel em = new EmployeeModel();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			System.out.println("here");
			while (resultSet.next()) {
				int eId = resultSet.getInt("eId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("address");
				//System.out.println(address);
				String contactNo = resultSet.getString("contactNo");

				EmployeeModel employeeModel = new EmployeeModel();
				employeeModel.seteId(eId);
				employeeModel.setFirstName(firstName);
				employeeModel.setLastName(lastName);
				employeeModel.setAddress(address);
				employeeModel.setContactNo(contactNo);
				employeeList.add(employeeModel);
			}
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		return employeeList;
	}
}