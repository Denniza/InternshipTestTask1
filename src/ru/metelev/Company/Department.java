package ru.metelev.Company;

import java.util.ArrayList;
import java.util.List;
//класс описывает департамент, хранит свой id и список работников которые в нём работают
//позволяет добавлять и удалять работников из департамента
public class Department {
    private int departmentId;
    private List<Employee> employeeList;

    public Department(int departmentId) {
        this.departmentId = departmentId;
        this.employeeList = new ArrayList<>();
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public boolean removeEmpoyee(Employee employee){
        return employeeList.remove(employee);
    }
}
