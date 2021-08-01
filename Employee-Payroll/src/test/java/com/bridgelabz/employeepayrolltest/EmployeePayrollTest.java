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
    public void givenEmployeePayrollData_WhenRetrieved_ShouldMatchNumberOfEmployees() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DATABASE_IO);
        Assert.assertEquals(3, employeePayrollData.size());
    }
	@Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabase() throws EmployeePayrollException {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(DATABASE_IO);
        employeePayrollService.updateEmployeeSalary("Charlie", 30000);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDatabase("Charlie");
        Assert.assertTrue(result);
    }
}
