package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.company.Company;
import ru.tsc.testtask.metelev.company.Department;
import ru.tsc.testtask.metelev.company.Employee;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
//Класс для манипулирования данными, позволяет загружать данные о работниках
public class CompanyServiceImpl implements uploadingEmployeeFromFileService {

    //метод проверяет валидность данных каждого работника
    //проверяется корректность Имени, Id департамента и зарплаты
    @Override
    public boolean employeeDataValidationService(String [] employeeData) {
        if(employeeData.length!=3) return false;//если в строке больше 3х параметров
        if(employeeData[0].isEmpty()||employeeData[0].matches(".*\\d+.*")) return false;//если Имя пустое или в нём есть цифры
        try {
            int departmentId = Integer.parseInt(employeeData[1]);
            if(departmentId<=0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        try{
            double salary = Double.parseDouble(employeeData[2]);
            if(salary<=0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //метод принимает путь к файлу, загружает список работников из файла и возвращает список объектов "Employee"
    // метод бросает IOException, при отсутствии файла с таким именем
    @Override
    public void uploadEmployeeServiceFromFile(String path,String textCode, Company company) throws IOException {
        Map<Integer, Department> departmentMap = company.getDepartmentMap();
        BufferedReader reader = new BufferedReader(new InputStreamReader
                                                    (new FileInputStream(path)
                                                    , Charset.forName(textCode)));
        String line;
        while((line = reader.readLine())!=null){
            String [] params = line.split(";");
            if(employeeDataValidationService(params)){
                if(departmentMap.get(Integer.parseInt(params[1]))!=null) {
                    departmentMap.get(Integer.parseInt(params[1]))
                            .getEmployeeList().add(new Employee(params[0]
                                                , params[1], params[2]));
                } else {
                    company.addDepartment(new Department(Integer.parseInt(params[1])));
                    departmentMap.get(Integer.parseInt(params[1]))
                            .getEmployeeList().add(new Employee(params[0]
                                                , params[1], params[2]));
                }
            } else {
                System.out.println("Ошибка в данных работника");
            }
        }//end cycle
    } //end method
}
