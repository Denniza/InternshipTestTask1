package ru.tsc.testtask.metelev.service;

import ru.tsc.testtask.metelev.model.Company;
import ru.tsc.testtask.metelev.model.Department;
import ru.tsc.testtask.metelev.model.Employee;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
//Класс для манипулирования данными, позволяет загружать данные о работниках
public class CompanyServiceImpl implements CompanyService {

    //метод проверяет валидность данных каждого работника
    //проверяется корректность Имени, Id департамента и зарплаты
    @Override
    public boolean employeeDataValidation(String[] employeeData) {
        if (employeeData.length != 3) return false;//если в строке больше 3х параметров
        if (employeeData[0].isEmpty() || employeeData[0].matches(".*[\\d^!@#$%&*]+.*"))
            return false;//если Имя пустое или в нём есть цифры
        try {
            int departmentId = Integer.parseInt(employeeData[1]);
            if (departmentId <= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        try {
            double salary = Double.parseDouble(employeeData[2]);
            if (salary <= 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    } //метод работает, тесты пройдены

    //метод принимает путь к файлу, кодировку и объект model, в который будут заносится данные.
    // загружает список работников из файла
    // метод бросает IOException, при отсутствии файла с таким именем
    @Override
    public void downloadEmployeeFromFile(String path, String textCode, Company company) throws IOException {
        Map<Integer, Department> departmentMap=company.getDepartmentMap();
        BufferedReader reader = new BufferedReader(new InputStreamReader
                                                    (new FileInputStream(path),
                                                            Charset.forName(textCode)));
        String line;
        while ((line=reader.readLine()) != null) {
            String[] params = line.split(";");
            if (employeeDataValidation(params)) {
                if (departmentMap.get(Integer.parseInt(params[1])) != null) {
                    departmentMap.get(Integer.parseInt(params[1]))
                            .getEmployeeList().add(new Employee(params[0], params[1], params[2]));
                } else {
                    company.addDepartment(new Department(Integer.parseInt(params[1])));
                    departmentMap.get(Integer.parseInt(params[1]))
                            .getEmployeeList().add(new Employee(params[0], params[1], params[2]));
                } //end inner if
            } else {
                System.out.println("Ошибка в данных работника");
            } //end if
        }//end while cycle
    } //end method
    //метод возвращает список возможных переводов между отделами, при котором средняя зарплата
    //увеличится в обоих отделах

    //метод записывает полученные результаты в файл, файл не перезаписывается, если он существует, данные добавляются в конец
    @Override
    public void uploadResults(String path, ArrayList<ArrayList<String>> results) {
        if(results.size() != 0) {
            try (FileWriter writer = new FileWriter(path, true)) {
                for (ArrayList<String> arr : results) {
                    writer.write(String.format("Возможен перевод из департамента %s в департамент %s , где средние зарплаты: %s и %s соответственно,\n" +
                            "следующих сотрудников: ", arr.get(0), arr.get(1), arr.get(2), arr.get(3)));
                    for (int i = 6; i < arr.size(); i++) {
                        writer.write(arr.get(i) + "\n");
                    }
                    writer.write(String.format("В результате перевода, средние зарплаты в департаментах изменятся на: %s и %s соответственно.\n", arr.get(4), arr.get(5)));
                }
            } catch (IOException e) {
                System.out.println("Некорректный путь для файла с результатами");
            }
        }
    }

    //метод создает список с возможными переводами между двумя отделами
    @Override
    public ArrayList<ArrayList<String>> getPossibleOptimalTransfersBetweenTwoDepartments(Department first, Department second) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Department richDepartment;// департамент с более высокой средней зарплатой
        Department notReachDepartment;
        ArrayList<Employee> transfers = new ArrayList<>();

        //определяем в каком отделе средняя зарплата выше и создаем копии объектов для работы
        //создаем копии департаментов для работы с данными и определения средних зарплат при переходах
        if (first.getAverageSalary().compareTo(second.getAverageSalary()) >= 0) {
            richDepartment = first;
            notReachDepartment = second;

        } else {
            richDepartment = second;
            notReachDepartment = first;
        }
        for (Employee employee : richDepartment.getEmployeeList()) {
            //если зарплата сотрудника ниже средней в своем отделе, но выше, чем во втором отделе,
            //то такого сотрудника можно перевести, записываем в результирующий набор
            if (employee.getSalary().compareTo(richDepartment.getAverageSalary()) < 0
                    && employee.getSalary().compareTo(notReachDepartment.getAverageSalary()) > 0) {
                transfers.add(employee);
            }
        }
        if(transfers.size()!=0) { // проверка на отсутствие записей
            //идем по списку возможных комбинаций выбирая работников из списка
            for (int[] combination : getPossibleCombinations(transfers.size())) {
                ArrayList<Employee> list = new ArrayList<>();
                for (int i = 0; i < combination.length; i++) {
                    if (combination[i] != 0) {
                        list.add(transfers.get(i));
                    }
                }
                //получаем данные об 1 из возможных переводов и добавляем в общий список
                result.add(getPossibleAverageSalaryChanges(richDepartment, notReachDepartment, list));
            }//end cycle
        }
        return result;
    }//end method

    //метод выдает список возможных комбинаций переводов, зависящий от количество человек, которых можно перевести
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
    private ArrayList<String> getPossibleAverageSalaryChanges(Department richDepartment,
                                                              Department notRichDepartment, ArrayList<Employee> employeeList){
        ArrayList<String> result = new ArrayList<>();
        result.add(String.valueOf(richDepartment.getDepartmentId()));//департамент из которого будут переводить
        result.add(String.valueOf(notRichDepartment.getDepartmentId()));//департамент куда
        result.add(richDepartment.getAverageSalary().toString());//средняя зарплата до перевода
        result.add(notRichDepartment.getAverageSalary().toString());//средняя зарплата до перевода
        //далее для каждого отдела считаем как изменится зарплата после перевода и добавляем значения в результат
        result.add(getSalaryChanges(richDepartment,employeeList,false).toString());
        result.add(getSalaryChanges(notRichDepartment,employeeList,true).toString());
        for(Employee employee : employeeList){
            result.add(employee.getName());
        }
        return result;
    }

    //метод считает изменения зарплаты в отделе в зависимости от того, придут туда новые сотрудники или наоборот, уйдут
    //третий параметр говорит о том, добавляются эти сотрудники или уходят
    private BigDecimal getSalaryChanges(Department department, List<Employee> employeeList, boolean add){
        BigDecimal result = department.getTotalDepartmentSalary();
        if(add) {
            for (Employee employee : employeeList) {
                result = result.add(employee.getSalary());
            }
            result = result.divide(BigDecimal.valueOf(department.getEmployeeList().size()
                                + employeeList.size()*1.0),2,BigDecimal.ROUND_HALF_UP);
            return result;
        } else{
            for (Employee employee : employeeList) {
                result = result.subtract(employee.getSalary());
            }
            result = result.divide(BigDecimal.valueOf(department.getEmployeeList().size()
                    - employeeList.size()*1.0),2,BigDecimal.ROUND_HALF_UP);
            return result;
        }
    }
}
