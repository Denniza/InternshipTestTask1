package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.model.Company;
import ru.tsc.testtask.metelev.model.Department;

import java.io.IOException;
import java.util.ArrayList;

public interface CompanyService {

    boolean employeeDataValidation(String [] employeeData);

    void downloadEmployeeFromFile(String path, String textCode, Company company) throws IOException;

    ArrayList<ArrayList<String>> getPossibleOptimalTransfersBetweenTwoDepartments(Department first, Department second);

    void uploadResults(String path, ArrayList<ArrayList<String>> results);
}
