package com.bridgelabz.employeepayroll;

import java.util.List;

public class EmployeePayrollService
{
	public enum IOService
	{
		DATABASE_IO
	}

	private List<EmployeePayrollData> employeePayrollData;

	public void updateEmployeeSalary(String name, double salary) throws EmployeePayrollException
	{
		int result = new EmployeePayrollDatabaseService().updateEmployeeData(name, salary);
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
			List<EmployeePayrollData> employeePayrollData = new EmployeePayrollDatabaseService()
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
				return this.employeePayrollData = new EmployeePayrollDatabaseService().readData();
			return this.employeePayrollData;
		} catch (EmployeePayrollException employeePayrollException)
		{
			throw new EmployeePayrollException("Cannot execute query",
					EmployeePayrollException.ExceptionType.CANNOT_EXECUTE_QUERY);
		}
	}
}
