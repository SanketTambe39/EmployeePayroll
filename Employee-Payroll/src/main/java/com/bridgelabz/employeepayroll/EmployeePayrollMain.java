package com.bridgelabz.employeepayroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class EmployeePayrollMain
{
	public static void main(String[] args)
	{
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String username = "root";
		String password = "Sanket@123";
		Connection connection;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException classNotFoundException)
		{
			throw new IllegalStateException("Cannot find the driver in the classpath: ", classNotFoundException);
		}
		listDrivers();
		try
		{
			System.out.println("Connecting to database: " + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection is successful: " + connection);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	private static void listDrivers()
	{
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements())
		{
			Driver driverClass = driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
}
