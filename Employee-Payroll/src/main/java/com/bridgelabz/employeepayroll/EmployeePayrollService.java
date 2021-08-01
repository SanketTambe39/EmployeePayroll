package com.bridgelabz.employeepayroll;

import java.util.List;

public class EmployeePayrollService
{
	public enum IOService {
        DATABASE_IO
    }

    private List<EmployeePayrollData> employeePayrollData;
    
    public void updateEmployeeSalary(String name, double salary) {
        int result = new EmployeePayrollDatabaseService().updateEmployeeData(name, salary);
        if(result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if(employeePayrollData != null) employeePayrollData.salary = salary;
    }

    private EmployeePayrollData getEmployeePayrollData(String name) {
        return this.employeePayrollData.stream()
                .filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean checkEmployeePayrollInSyncWithDatabase(String name) {
        List<EmployeePayrollData> employeePayrollData = new EmployeePayrollDatabaseService().getEmployeePayrollData(name);
        return employeePayrollData.get(0).equals(getEmployeePayrollData(name));
    }


    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        if(ioService.equals(IOService.DATABASE_IO))
        	return this.employeePayrollData = new EmployeePayrollDatabaseService().readData();
        return this.employeePayrollData;
    }
}
