package ru.tsc.testtask.metelev.controller;

import ru.tsc.testtask.metelev.model.Company;
import ru.tsc.testtask.metelev.service.CompanyServiceImpl;
import ru.tsc.testtask.metelev.view.CompanyView;

import java.io.IOException;
import java.util.ArrayList;

public class CompanyController {
     private Company company;
     private CompanyServiceImpl service;
     private CompanyView view;

    public CompanyController() {
        this.service = new CompanyServiceImpl();
        this.view = new CompanyView();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static void main(String[] args) {
        CompanyController companyController = new CompanyController();
        companyController.setCompany(new Company(companyController.view.showGreeting()));// выводим приветствие на экран
        while(true){
            try {
                companyController.service.downloadEmployeeFromFile(args[0], args[1], companyController.company);
                break;
            }catch(IOException e){
                    System.out.println("Введите корректный путь к файлу - ошибка чтения данных");
            }
        }
        int [] departments;
        while(true){
            departments = companyController.view.askUserForDepartments();
            //проверяется валидность введенных данных, существуют ли такие департаменты,
            // если да, то вызывается метод сервиса
                if(!companyController.company.getDepartmentMap().containsKey(departments[0])||!companyController.company.getDepartmentMap().containsKey(departments[1])) {
                    System.out.println("Какого-либо из указанных департаментов не существует, введите корректные номера департаментов");
                } else{
                    ArrayList<ArrayList<String>> results = companyController.service.getPossibleOptimalTransfersBetweenTwoDepartments
                            (companyController.company.getDepartmentMap()
                                    .get(departments[0]), companyController.company.getDepartmentMap().get(departments[1]));
                    companyController.view.showPossibleOptimalTransfersBetweenTwoDepartments(results);
                    companyController.service.uploadResults(args[2],results);
                 }
                if(companyController.view.showExitOrContinue()) break;
        }
    }
}
