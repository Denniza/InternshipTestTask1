package ru.metelev.MainApp;

import ru.metelev.Company.Company;
import ru.metelev.Company.Department;
import ru.metelev.Service.ServiceImpl;
import sun.applet.Main;

public class MainApp {
     private Company company;
    private ServiceImpl service;

    public MainApp() {
        this.company = new Company();
        this.service = service;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ServiceImpl getService() {
        return service;
    }

    public void setService(ServiceImpl service) {
        this.service = service;
    }

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.company.setCompanyName("RomashkaIndustries");

    }
}
