package com.bridgelabz.employeepayrolltest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.employeepayroll.EmployeePayrollData;
import com.bridgelabz.employeepayroll.EmployeePayrollException;
import com.bridgelabz.employeepayroll.EmployeePayrollService;

import static com.bridgelabz.employeepayroll.EmployeePayrollService.IOService.DATABASE_IO;

public class EmployeePayrollTest
{
	@Test
	public void givenEmployeePayrollData_WhenRetrieved_ShouldMatchNumberOfEmployees() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DATABASE_IO);
		Assert.assertEquals(3, employeePayrollData.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabase() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DATABASE_IO);
		employeePayrollService.updateEmployeeSalary("Charlie", 30000);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDatabase("Charlie");
		Assert.assertTrue(result);
	}

	@Test
	public void givenEmployeePayrollData_ShouldNumberOfEmployeesWithinDateRange() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DATABASE_IO,
				"2018-06-01", "2020-04-30");
		Assert.assertEquals(3, employeePayrollData.size());
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnSumOfFemaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(200000, employeePayrollService.readEmployeePayrollData("SUM", "F"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnSumOfMaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(3030000, employeePayrollService.readEmployeePayrollData("SUM", "M"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnAverageOfFemaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(200000, employeePayrollService.readEmployeePayrollData("AVG", "F"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnAverageOfMaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(1515000, employeePayrollService.readEmployeePayrollData("AVG", "M"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnMinimumOfFemaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(200000, employeePayrollService.readEmployeePayrollData("MIN", "F"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnMinimumOfMaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(30000, employeePayrollService.readEmployeePayrollData("MIN", "M"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnMaximumOfFemaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(200000, employeePayrollService.readEmployeePayrollData("MAX", "F"));
	}
	
	@Test
	public void givenEmployeePayrollData_ShouldReturnMaximumOfMaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(3000000, employeePayrollService.readEmployeePayrollData("MAX", "M"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnNumberOfFemaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(1, employeePayrollService.readEmployeePayrollData("COUNT", "F"));
	}

	@Test
	public void givenEmployeePayrollData_ShouldReturnNumberOfMaleEmployeeSalaries() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Assert.assertEquals(2, employeePayrollService.readEmployeePayrollData("COUNT", "M"));
	}
}
