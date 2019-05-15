package ru.tsc.testtask.metelev.controller;

import ru.tsc.testtask.metelev.model.Company;
import ru.tsc.testtask.metelev.model.TargetDepartments;
import ru.tsc.testtask.metelev.model.TransferInfo;
import ru.tsc.testtask.metelev.service.EmployeeTransferService;
import ru.tsc.testtask.metelev.service.EmployeeIOService;
import ru.tsc.testtask.metelev.view.CompanyView;

import java.io.IOException;
import java.util.ArrayList;

public class CompanyController {
     private Company company;
     private EmployeeIOService IOService;
     private CompanyView view;
     private EmployeeTransferService transferService;

    public CompanyController() {
        this.IOService = new EmployeeIOService();
        this.view = new CompanyView();
        this.transferService = new EmployeeTransferService();
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static void main(String[] args) {
        CompanyController companyController = new CompanyController();
        companyController.setCompany(new Company(companyController.view.showGreeting()));
        while(true){
            try {
                companyController.IOService.downloadEmployeeFromFile(args[0], args[1], companyController.company);
                break;
            }catch(IOException e){
                    System.out.println("Введите корректный путь к файлу - ошибка чтения данных");
            }
        }
        TargetDepartments targetDepartments;
        while(true){
            targetDepartments = companyController.view.askUserForDepartments();
                if(!companyController.company.getDepartmentMap().containsKey(targetDepartments.getFirstDepartment())
                        ||!companyController.company.getDepartmentMap().containsKey(targetDepartments.getSecondDepartment())) {
                    System.out.println("Какого-либо из указанных департаментов не существует, введите корректные номера департаментов");
                } else{
                    ArrayList<TransferInfo> results = companyController.transferService.getPossibleOptimalTransfersBetweenTwoDepartments
                            (companyController.company.getDepartmentMap().get(targetDepartments.getFirstDepartment()),
                                    companyController.company.getDepartmentMap().get(targetDepartments.getSecondDepartment()));
                    companyController.view.showPossibleOptimalTransfersBetweenTwoDepartments(results);
                    companyController.IOService.uploadResults(args[2],results);
                 }
                if(companyController.view.showExitOrContinue()) break;
        }
    }
}
