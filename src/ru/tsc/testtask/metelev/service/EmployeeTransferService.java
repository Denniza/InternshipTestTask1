package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.model.Department;
import ru.tsc.testtask.metelev.model.Employee;
import ru.tsc.testtask.metelev.model.TransferInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeTransferService {
    //метод возвращает список возможных переводов между отделами, при котором средняя зарплата
    //увеличится в обоих отделах
    public  ArrayList<TransferInfo> getPossibleOptimalTransfersBetweenTwoDepartments(Department first, Department second) {
        ArrayList<TransferInfo> result = new ArrayList<>();
        Department richDepartment = (first.getAverageSalary().compareTo(second.getAverageSalary()) >= 0) ? first:second;
        Department notReachDepartment = (first.getAverageSalary().compareTo(second.getAverageSalary()) >= 0) ? second:first;
        if(richDepartment.getEmployeeList().size()!=0) {
            for (int[] combination : getPossibleCombinations(richDepartment.getEmployeeList().size())) {
                ArrayList<Employee> transferList = new ArrayList<>();
                for (int i = 0; i < combination.length; i++) {
                    if (combination[i] != 0) {
                        transferList.add(richDepartment.getEmployeeList().get(i));
                    }
                }
                //считаем среднюю зарплату для каждой комбинации работников и если она удовлетворяет условию
                //то заносим в результат
                BigDecimal resultSalary = new BigDecimal(0);
                for(Employee employee: transferList){
                    resultSalary = resultSalary.add(employee.getSalary());
                }
                resultSalary = resultSalary.divide(new BigDecimal(transferList.size()),2,BigDecimal.ROUND_HALF_UP);
                if(resultSalary.compareTo(richDepartment.getAverageSalary()) < 0
                        && resultSalary.compareTo(notReachDepartment.getAverageSalary()) > 0){
                    result.add(getPossibleAverageSalaryChanges(richDepartment, notReachDepartment, transferList));
                }
            }
        }
        return result;
    }
    private ArrayList<int[]> getPossibleCombinations(int numberOfElements) {
        ArrayList<int[]> result = new ArrayList<>();
        int[] arr = new int[numberOfElements];
        outer:
        while (true) {
            int i = numberOfElements - 1;//ставим курсор в самую правую ячейку
            while (arr[i] == 1) {//движемся влево, если ячейка переполнена
                arr[i] = 0;//записываем в ячейку 0, т.к. идет перенос разряда
                i--;//сдвиг влево
                //если перенос влево невозможен, значит перебор закончен
                if (i < 0) break outer;
            }
            arr[i]++;
            result.add(Arrays.copyOf(arr, arr.length));
        }
        return result;
    }

    //метод считает зарплату которая будет в отделах при возможном переводе, возвращает список со всей информацией для пердставления
    private TransferInfo getPossibleAverageSalaryChanges(Department richDepartment,
                                                         Department notRichDepartment, ArrayList<Employee> employeeList){
        TransferInfo transferInfo = new TransferInfo(richDepartment,notRichDepartment,richDepartment.getAverageSalary(),
                notRichDepartment.getAverageSalary(),getSalaryChanges(richDepartment,employeeList,false),
                getSalaryChanges(notRichDepartment,employeeList,true),employeeList);
        return transferInfo;
    }

    //метод считает изменения зарплаты в отделе в зависимости от того, придут туда новые сотрудники или наоборот, уйдут
    //третий параметр говорит о том, добавляются эти сотрудники или уходят
    private BigDecimal getSalaryChanges(Department department, List<Employee> employeeList, boolean add){
        BigDecimal result = department.getTotalDepartmentSalary();
        for (Employee employee : employeeList) {
            result = result.add(employee.getSalary().multiply(new BigDecimal((add?1:-1))));
        }
        result = result.divide(new BigDecimal(department.getEmployeeList().size()
                + employeeList.size()*(add?1:-1)),2,BigDecimal.ROUND_HALF_UP);
        return result;
    }
}

