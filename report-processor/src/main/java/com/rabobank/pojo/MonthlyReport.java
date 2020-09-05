package com.rabobank.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlyReport {
    private long reference;
    private String accountNumber;
    private String Description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;
    private String reason;
}
