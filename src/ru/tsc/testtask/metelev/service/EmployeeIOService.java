package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.model.Company;
import ru.tsc.testtask.metelev.model.Department;
import ru.tsc.testtask.metelev.model.Employee;
import ru.tsc.testtask.metelev.model.TransferInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

import java.util.Map;

public class EmployeeIOService {
    //метод проверяет валидность данных каждого работника
    //проверяется корректность Имени, Id департамента и зарплаты и заносит в нужный департамент
    public void downloadEmployeeFromFile(String path, String textCode, Company company) throws IOException {
        Map<Integer, Department> departmentMap = company.getDepartmentMap();
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(path),
                        Charset.forName(textCode)));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] params = line.split(";");
            employeeDataValidation(params, departmentMap);
        }
    }

    public void uploadResults(String path, ArrayList<TransferInfo> results) {
        if (results.size() != 0) {
            try (FileWriter writer = new FileWriter(path, true)) {
                for (TransferInfo arr : results) {
                    writer.write(String.format("Возможен перевод из департамента %s в департамент %s , где средние зарплаты: %s и %s соответственно,\n" +
                                    "следующих сотрудников: ", arr.getRich().getDepartmentId(), arr.getNotRich().getDepartmentId(), arr.getRichAvgSalaryBeforeTransfer(),
                            arr.getNotRichAvgSalaryBeforeTransfer()));
                    for (Employee employee : arr.getEmployeesForTransfer()) {
                        writer.write(employee.getName() + "\n");
                    }
                    writer.write(String.format("В результате перевода, средние зарплаты в департаментах изменятся на: %s и %s соответственно.\n",
                            arr.getRichAvgSalaryAfterTransfer(), arr.getNotRichAvgSalaryAfterTransfer()));
                }
            } catch (IOException e) {
                System.out.println("Некорректный путь для файла с результатами");
            }
        }
    }

    //метод проверяет валидность данных каждого работника
    //проверяется корректность Имени, Id департамента и зарплаты и заносит в нужный департамент
    private void employeeDataValidation(String[] employeeData, Map<Integer, Department> departmentMap) {
        int departmentId = 0;
        try {
            if (employeeData.length != 3) throw new Exception("В строке больше 3-х параметров");
            if (employeeData[0].isEmpty() || employeeData[0].matches(".*[\\d^!@#$%&*]+.*"))
                throw new Exception("Имя пустое или содержатся недопустимые символы");
            departmentId = Integer.parseInt(employeeData[1]);
            if (departmentId <= 0) throw new Exception("Некорректное значение id департамента");
            double salary = Double.parseDouble(employeeData[2]);
            if (salary <= 0) throw new Exception("некорректное значение зарплаты");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        departmentMap.putIfAbsent(departmentId, new Department(departmentId));
        departmentMap.get(departmentId).getEmployeeList().add(new Employee(employeeData[0], employeeData[2]));
    }
}
