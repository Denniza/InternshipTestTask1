package ru.tsc.testtask.metelev.model;

import java.math.BigDecimal;

public class Employee {
    private String name;
    private BigDecimal salary;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\''+
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, String salary) {
        this.name = name;
        this.salary = BigDecimal.valueOf(Double.parseDouble(salary));

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
