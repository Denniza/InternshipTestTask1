package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.company.Company;
import ru.tsc.testtask.metelev.company.Department;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DownloadEmployeeFromFileService {

    boolean employeeDataValidation(String [] employeeData);

    void downloadEmployeeFromFile(String path, String textCode, Company company) throws IOException;

    ArrayList<ArrayList<String>> getPossibleOptimalTransfersBetweenTwoDepartments(Department first, Department second);
}
