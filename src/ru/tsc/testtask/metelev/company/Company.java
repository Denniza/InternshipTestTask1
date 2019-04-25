package ru.tsc.testtask.metelev.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Company {
    private String companyName;
    private Map<Integer ,Department> departmentMap;


    public Company(String name) {
        this.companyName = name;
        this.departmentMap = new HashMap<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    //Добавляет департамент если департамента с таким ID нет и возвращает True, иначе false и замены не происходит
    public boolean addDepartment(Department department){
        if(!departmentMap.containsKey(department.getDepartmentId())){
            departmentMap.put(department.getDepartmentId(), department);
            return true;
        } else {
            return false;
        }
    }

    public Map<Integer, Department> getDepartmentMap() {
        return departmentMap;
    }
    //метод возвращает всех работников из всех департаментов
    public ArrayList<Employee> getAllEmployeeList() {
        ArrayList<Employee> result;
        result = new ArrayList<>();
        for(Map.Entry<Integer,Department> element: departmentMap.entrySet()){
            result.addAll(element.getValue().getEmployeeList());
        }
        return result;
    }

}
