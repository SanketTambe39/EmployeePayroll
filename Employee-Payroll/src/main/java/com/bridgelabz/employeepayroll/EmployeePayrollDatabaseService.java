package com.bridgelabz.employeepayroll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDatabaseService
{
	EmployeePayrollDatabaseService()
	{

	}

	private Connection getConnection() throws SQLException
	{
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String username = "root";
		String password = "Sanket@123";
		Connection connection = null;
		System.out.println("Connecting to database: " + jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Connection is successful: " + connection);
		return connection;
	}

	public List<EmployeePayrollData> readData(LocalDate start, LocalDate end)
	{
		String sql = "select * from employee_payroll";
		List<EmployeePayrollData> employeePayrollData = new ArrayList();
		try (Connection connection = this.getConnection())
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			employeePayrollData = this.getEmployeePayrollData(resultSet);
		} catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		return employeePayrollData;
	}

	private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet)
	{
		List<EmployeePayrollData> employeePayrollData = new ArrayList();
		try
		{
			while (resultSet.next())
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollData.add(new EmployeePayrollData(id, name, salary, startDate));
			}
		} catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		return employeePayrollData;
	}
}
