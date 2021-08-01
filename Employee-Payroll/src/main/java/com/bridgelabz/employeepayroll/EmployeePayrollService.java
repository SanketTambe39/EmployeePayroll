package com.bridgelabz.employeepayroll;

import java.util.List;

public class EmployeePayrollService
{
	public enum IOService {
        DATABASE_IO
    }

    private List<EmployeePayrollData> employeePayrollData;

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
        if(ioService.equals(IOService.DATABASE_IO))
            return this.employeePayrollData = new EmployeePayrollDatabaseService().readData(null, null);
        return this.employeePayrollData;
    }
}
