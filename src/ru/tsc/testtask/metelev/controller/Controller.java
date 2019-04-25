package ru.tsc.testtask.metelev.controller;

import ru.tsc.testtask.metelev.company.Company;
import ru.tsc.testtask.metelev.company.Department;
import ru.tsc.testtask.metelev.company.Employee;
import ru.tsc.testtask.metelev.service.CompanyServiceImpl;

import java.io.IOException;
import java.util.Map;

public class Controller {
     private Company company;
     private CompanyServiceImpl service;

    public Controller() {
        this.service = new CompanyServiceImpl();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static void main(String[] args) {
        //создаём контроллер и компанию с департаментами
        Controller controller = new Controller();
        Company company = new Company("RomashkaIndustries");
        controller.setCompany(company);
        while(true){
        try {//считываем из файла список сотрудников и заносим в компанию
            controller.service.uploadEmployeeServiceFromFile("C:\\Users\\ametelev\\IdeaProjects\\InternshipTask1\\Список сотрудников.txt"
                    ,"windows-1251", controller.company);
            break;
        }catch(IOException e){
                System.out.println("Введите корректный путь к файлу - ошибка чтения данных");
            }
        }

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
