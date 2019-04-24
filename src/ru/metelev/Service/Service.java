package ru.metelev.Service;

import ru.metelev.Company.Department;
import ru.metelev.Company.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Service {

    List<Employee> uploadEmployeeServiceFromFile(String path) throws IOException;

    void sortEmployeesBetweenDepartments(Map<Integer, Department> departmentMap, List<Employee> employeeList);

}
