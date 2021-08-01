package com.bridgelabz.employeepayroll;

import java.time.LocalDate;
import java.util.List;

public class EmployeePayrollService
{
	public enum IOService
	{
		DATABASE_IO
	}

	private List<EmployeePayrollData> employeePayrollData;
	private EmployeePayrollDatabaseService employeePayrollDatabaseService;

	public EmployeePayrollService()
	{
		employeePayrollDatabaseService = EmployeePayrollDatabaseService.getInstance();
	}

	public void updateEmployeeSalary(String name, double salary) throws EmployeePayrollException
	{
		int result = EmployeePayrollDatabaseService.getInstance().updateEmployeeData(name, salary);
		if (result == 0)
			throw new EmployeePayrollException("Salary update failed",
					EmployeePayrollException.ExceptionType.UPDATE_FAILED);
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	private EmployeePayrollData getEmployeePayrollData(String name)
	{
		return this.employeePayrollData.stream()
				.filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
	}

	public boolean checkEmployeePayrollInSyncWithDatabase(String name) throws EmployeePayrollException
	{
		try
		{
			List<EmployeePayrollData> employeePayrollData = EmployeePayrollDatabaseService.getInstance()
					.getEmployeePayrollData(name);
			return employeePayrollData.get(0).equals(getEmployeePayrollData(name));
		} catch (EmployeePayrollException employeePayrollException)
		{
			throw new EmployeePayrollException("Cannot execute query",
					EmployeePayrollException.ExceptionType.CANNOT_EXECUTE_QUERY);
		}
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) throws EmployeePayrollException
	{
		try
		{
			if (ioService.equals(IOService.DATABASE_IO))
				return this.employeePayrollData = EmployeePayrollDatabaseService.getInstance().readData();
			return this.employeePayrollData;
		} catch (EmployeePayrollException employeePayrollException)
		{
			throw new EmployeePayrollException("Cannot execute query",
					EmployeePayrollException.ExceptionType.CANNOT_EXECUTE_QUERY);
		}
	}

	public int readEmployeePayrollData(String action, String gender) throws EmployeePayrollException
	{
		try
		{
			return EmployeePayrollDatabaseService.getInstance().readData(action, gender);
		} catch (EmployeePayrollException employeePayrollException)
		{
			throw new EmployeePayrollException("Cannot execute query",
					EmployeePayrollException.ExceptionType.CANNOT_EXECUTE_QUERY);
		}
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService, LocalDate start, LocalDate end)
			throws EmployeePayrollException
	{
		try
		{
			if (ioService.equals(IOService.DATABASE_IO))
				return employeePayrollDatabaseService.readData(start, end);
			return this.employeePayrollData;
		} catch (EmployeePayrollException employeePayrollException)
		{
			throw new EmployeePayrollException("Cannot execute query",
					EmployeePayrollException.ExceptionType.CANNOT_EXECUTE_QUERY);
		}
	}

	public void addNewEmployee(String name, double salary, LocalDate startDate, String gender)
			throws EmployeePayrollException
	{
		employeePayrollData.add(employeePayrollDatabaseService.addNewEmployee(name, salary, startDate, gender));
	}
}
