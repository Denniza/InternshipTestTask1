package ru.metelev.Controller;

import ru.metelev.Company.Company;
import ru.metelev.Company.Department;
import ru.metelev.Company.Employee;
import ru.metelev.Service.ServiceImpl;

import java.io.IOException;
import java.util.Map;

public class Controller {
     private Company company;
     private ServiceImpl service;

    public Controller() {
        this.company = new Company();
        this.service = new ServiceImpl();
    }


    public static void main(String[] args) {
        //создаём контроллер и компанию с департаментами
        Controller controller = new Controller();
        controller.company.setCompanyName("RomashkaIndustries");
        controller.company.addDepartment(new Department(1),
                                        new Department(2),
                                        new Department(3));
        try{//считываем из файла список сотрудников и заносим в компанию
        controller.company.setAllEmployeeList
                (controller.service.uploadEmployeeServiceFromFile
                        ("C:\\Users\\ametelev\\IdeaProjects\\InternshipTask1\\Список сотрудников.txt"));}
        catch (IOException e){
            System.out.println("Введите корректный путь к файлу - ошибка чтения данных");
        }
        //сортируем работников по департаментам
        controller.service.sortEmployeesBetweenDepartments(controller.company.getDepartmentMap()
                                                ,controller.company.getAllEmployeeList());
        //это просто проверка на корректность сортировки, вообще это был бы метод View, который отображал бы данные
        //
        for(Map.Entry entry:controller.company.getDepartmentMap().entrySet()){
            Department department = (Department) entry.getValue();
            System.out.print("Департамент: " + entry.getKey() + " список сотрудников: ");
            for(Employee employee: department.getEmployeeList()){
                System.out.println(employee);
            }
            System.out.println("");
        } //end cycle
    }
}
