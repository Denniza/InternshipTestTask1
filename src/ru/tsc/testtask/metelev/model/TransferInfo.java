package ru.tsc.testtask.metelev.model;

import java.math.BigDecimal;
import java.util.List;

public class TransferInfo {
    private Department rich;
    private Department notRich;
    private BigDecimal richAvgSalaryBeforeTransfer;
    private BigDecimal notRichAvgSalaryBeforeTransfer;
    private BigDecimal richAvgSalaryAfterTransfer;
    private BigDecimal notRichAvgSalaryAfterTransfer;
    private List<Employee> employeesForTransfer;

    public TransferInfo(Department rich, Department notRich, BigDecimal richAvgSalaryBeforeTransfer,
                        BigDecimal notRichAvgSalaryBeforeTransfer, BigDecimal richAvgSalaryAfterTransfer,
                            BigDecimal notRichAvgSalaryAfterTransfer, List<Employee> employeesForTransfer) {
        this.rich = rich;
        this.notRich = notRich;
        this.richAvgSalaryBeforeTransfer = richAvgSalaryBeforeTransfer;
        this.notRichAvgSalaryBeforeTransfer = notRichAvgSalaryBeforeTransfer;
        this.richAvgSalaryAfterTransfer = richAvgSalaryAfterTransfer;
        this.notRichAvgSalaryAfterTransfer = notRichAvgSalaryAfterTransfer;
        this.employeesForTransfer = employeesForTransfer;
    }

    public Department getRich() {
        return rich;
    }

    public Department getNotRich() {
        return notRich;
    }

    public BigDecimal getRichAvgSalaryBeforeTransfer() {
        return richAvgSalaryBeforeTransfer;
    }

    public BigDecimal getNotRichAvgSalaryBeforeTransfer() {
        return notRichAvgSalaryBeforeTransfer;
    }

    public BigDecimal getRichAvgSalaryAfterTransfer() {
        return richAvgSalaryAfterTransfer;
    }

    public BigDecimal getNotRichAvgSalaryAfterTransfer() {
        return notRichAvgSalaryAfterTransfer;
    }

    public List<Employee> getEmployeesForTransfer() {
        return employeesForTransfer;
    }
}
