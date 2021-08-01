package com.bridgelabz.employeepayrolltest;

import java.time.LocalDate;
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
		LocalDate start = LocalDate.of(2020, 03, 01);
		LocalDate end = LocalDate.of(2020, 04, 30);
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DATABASE_IO,
				start, end);
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

	@Test
	public void givenNewEmployee_WhenAdded_ShouldSyncWithDatabase() throws EmployeePayrollException
	{
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeePayrollData(DATABASE_IO);
		employeePayrollService.addNewEmployee("Sanket", 130000, LocalDate.now(), "M");
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDatabase("Sanket");
		Assert.assertTrue(result);
	}
}
