package ru.metelev.Company;


import ru.metelev.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//основной класс, аналог VIEW, хранит в себе департаменты и список всех сотрудников, с помощью объекта Service
//манипулирует данными
//предусмотрено 3 конструктора, можно передать департаменты при создании, либо добавить их позже
public class Company {
    private String companyName;
    private Map<Integer ,Department> departmentMap;
    private ArrayList<Employee> allEmployeeList;


    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
        this.companyName = "Unknown";
        this.departmentMap = new HashMap<>();
        this.allEmployeeList = new ArrayList<>();
    }
    public Company(String name, Department... args) {
        this.companyName = name;
        this.departmentMap = new HashMap<>();
        this.allEmployeeList = new ArrayList<>();
        for(Department department:args){
            departmentMap.put(department.getDepartmentId(), department);
        }
    }

    public void setAllEmployeeList(List<Employee> allEmployeeList) {
        this.allEmployeeList = (ArrayList<Employee>) allEmployeeList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void addDepartment(Department department){
        departmentMap.put(department.getDepartmentId(), department);
    }

    public void addDepartment(Department... departmentList){
        for (Department department:departmentList){
            departmentMap.put(department.getDepartmentId(),department);
        }
    }

    public Map<Integer, Department> getDepartmentMap() {
        return departmentMap;
    }

    public ArrayList<Employee> getAllEmployeeList() {
        return allEmployeeList;
    }

}
