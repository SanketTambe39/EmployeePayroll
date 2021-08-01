package com.bridgelabz.employeepayroll;

import java.time.LocalDate;

public class EmployeePayrollData
{
	public int id;
	public String name;
	public double salary;
	public LocalDate startDate;

	public EmployeePayrollData(Integer id, String name, Double salary)
	{
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	public EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
        this(id, name, salary);
        this.startDate = startDate;
    }
	@Override
    public String toString() {
        return "ID = " + id + ", Name = " + name + ", Salary = " + salary;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EmployeePayrollData employeePayrollData = (EmployeePayrollData) object;
        return id == employeePayrollData.id && Double.compare(employeePayrollData.salary, salary) == 0 && name.equals(employeePayrollData.name);
    }
}
