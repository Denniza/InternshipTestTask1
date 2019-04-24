package ru.metelev.Company;

public class Employee {
    private String name;
    private String surName;
    private String lastName;
    private int departmentId;
    private int salary;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }

    public Employee(String... args) {
    this.surName = args[0];
    this.name = args[1];
    this.lastName = args[2];
    this.departmentId = Integer.parseInt(args[3]);
    this.salary = Integer.parseInt(args[4]);

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surnName) {
        this.surName = surnName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
