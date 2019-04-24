package ru.metelev.Service;

import ru.metelev.Company.Department;
import ru.metelev.Company.Employee;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//объект для оказания сервисов компании, здсь прописываем логику работы с данными
public class ServiceImpl implements Service {
    //метод принимает путь к файлу, загружает список работников из файла и возвращает список объектов "Employee"
    // метод бросает IOException, при отсутствии файла с таким именем
    @Override
    public List<Employee> uploadEmployeeServiceFromFile(String path) throws IOException {
        List<Employee> allEmployeeList = new ArrayList<>();
        String [] lines = Files.readAllLines(Paths.get(path), Charset.forName("windows-1251")).toArray(new String[0]);
        for(String element:lines){
            String [] params = element.split(";");
            allEmployeeList.add(new Employee(params));
        } //end cycle
        return allEmployeeList;
    } //end method
    //метод сортирует всех работников по департаментам
    @Override
    public void sortEmployeesBetweenDepartments(Map<Integer, Department> departmentMap, List<Employee> employeeList) {
        for(Employee employee:employeeList) {
            if (departmentMap.containsKey(employee.getDepartmentId())) {
                departmentMap.get(employee.getDepartmentId())
                        .addEmployee(employee); //для каждого работника получаем нужный
                // депратамент и добавляем работника в список
                //работников этого департамента, если он существует
            }
        }
    }//end method
}
