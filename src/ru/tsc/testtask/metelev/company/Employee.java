package ru.tsc.testtask.metelev.company;

import java.math.BigDecimal;

public class Employee {
    private String name;
    private int departmentId;
    private BigDecimal salary;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\''+
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, String departmentId, String salary) {
        this.name = name;
        this.departmentId = Integer.parseInt(departmentId);
        this.salary = BigDecimal.valueOf(Double.parseDouble(salary));

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    private void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
