package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.company.Company;
import ru.tsc.testtask.metelev.company.Department;
import ru.tsc.testtask.metelev.company.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface uploadingEmployeeFromFileService {

    boolean employeeDataValidationService(String [] employeeData);

    void uploadEmployeeServiceFromFile(String path, String textCode, Company company) throws IOException;
}
