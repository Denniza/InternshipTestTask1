package ru.tsc.testtask.metelev.controller;

import ru.tsc.testtask.metelev.company.Company;
import ru.tsc.testtask.metelev.service.CompanyServiceImpl;
import ru.tsc.testtask.metelev.view.MyView;

import java.io.IOException;

public class Controller {
     private Company company;
     private CompanyServiceImpl service;
     private MyView view;

    public Controller() {
        this.service = new CompanyServiceImpl();
        this.view = new MyView();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.setCompany(new Company(controller.view.showGreeting()));// выводим приветствие на экран
        while(true){
                try {
                    controller.service.downloadEmployeeFromFile(args[0]
                    ,args[1], controller.company);
                    break;
                }catch(IOException e){
                    System.out.println("Введите корректный путь к файлу - ошибка чтения данных");
                    break;
            }
        }
        int [] departments;
        while(true){
            departments = controller.view.askUserForDepartments();
            //проверяется валидность введенных данных, существуют ли такие департаменты,
            // если да, то вызывается метод сервиса
                if(!controller.company.getDepartmentMap().containsKey(departments[0])||!controller.company.getDepartmentMap().containsKey(departments[1]))
                System.out.println("Какого-либо из указанных департаментов не существует, введите корректные номера департаментов");
                else {
                controller.view.showPossibleOptimalTransfersBetweenTwoDepartments(controller.service.getPossibleOptimalTransfersBetweenTwoDepartments
                        (controller.company.getDepartmentMap()
                                .get(departments[0]),controller.company.getDepartmentMap().get(departments[1])));
            }
            if(controller.view.showExitOrContinue()) break;
        }
    }
}
