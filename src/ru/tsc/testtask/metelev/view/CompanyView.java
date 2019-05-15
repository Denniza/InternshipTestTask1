package ru.tsc.testtask.metelev.view;

import ru.tsc.testtask.metelev.model.Employee;
import ru.tsc.testtask.metelev.model.TargetDepartments;
import ru.tsc.testtask.metelev.model.TransferInfo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class CompanyView {


    public String showGreeting(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать!");
        System.out.println("Программа позволяет считать данные о сотрудниках из файла, сформировать возможные варианты перевода");
        System.out.println("сотрудников между департаментами, при которых средняя зарплата увеличивается" +
                " в обоих отделах.\nУкажите путь к вашему файлу с данными работников в аргументах программы вместе с кодировкой файла и " +
                "путь к файлу для записи результатов в args[0], args[1] и args[2] соответственно.");
        System.out.println("Для начала работы введите название компании:");
        return scanner.nextLine();
    }

    public boolean showExitOrContinue(){
        System.out.println("Введите любой символ чтобы продолжить, для выхода введите 'exit'");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line.equals("exit");

    }

    public TargetDepartments askUserForDepartments(){
        System.out.println("Введите поочередно номера департаментов, в которых вы хотите осуществить перевод сотрудников:");
        TargetDepartments targetDepartments = new TargetDepartments();
        while(true) {
            try {
                Scanner scanner1 = new Scanner(System.in);
                targetDepartments.setFirstDepartment(scanner1.nextInt());
                targetDepartments.setSecondDepartment(scanner1.nextInt());
                break;
            } catch (InputMismatchException e){
                System.out.println("Некорректные данные, введите целочисленные значения ID департаментов");
            }
        }
        return targetDepartments;
    }

    public void showPossibleOptimalTransfersBetweenTwoDepartments(ArrayList<TransferInfo> list){
        if(list.size() == 0) {
            System.out.println("Отсутствуют возможные переводы.");
            return;
        }
        for(TransferInfo transfer : list){
            System.out.printf("Возможен перевод из департамента %s в департамент %s , где средние зарплаты: %s и %s соответственно,\n" +
                    "следующих сотрудников: ",transfer.getRich().getDepartmentId(),transfer.getNotRich().getDepartmentId(),transfer.getRichAvgSalaryBeforeTransfer(),
                            transfer.getNotRichAvgSalaryBeforeTransfer());
            for (Employee employee:transfer.getEmployeesForTransfer()){
                System.out.print(employee.getName() + "\n");
            }
            System.out.printf("В результате перевода, средние зарплаты в департаментах изменятся на: %s и %s соответственно.\n",
                    transfer.getRichAvgSalaryAfterTransfer(),transfer.getNotRichAvgSalaryAfterTransfer());
        }
    }
}
