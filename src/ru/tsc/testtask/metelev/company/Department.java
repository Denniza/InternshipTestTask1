package ru.tsc.testtask.metelev.company;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//класс описывает департамент, хранит свой id и список работников которые в нём работают
public class Department {
    private int departmentId;
    private List<Employee> employeeList;

    public Department(int departmentId) {
        this.departmentId = departmentId;
        this.employeeList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", employeeList=" + employeeList +
                '}';
    }

    public BigDecimal getAverageSalary(){
        BigDecimal result = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
        for(Employee employee:employeeList){
            result = result.add(employee.getSalary());
        }
        result = result.divide(BigDecimal.valueOf(employeeList.size()*1.0),2,BigDecimal.ROUND_HALF_UP);
        return result;
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

}
