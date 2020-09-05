package com.rabobank.utils;

import com.rabobank.pojo.MonthlyReport;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class FileUtils {
    public static Optional<String> findExtension(String fileName)
    {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));

    }
    public static MonthlyReport findReference(List<MonthlyReport> reportList, final long referenceNo)
    {
        MonthlyReport monthlyReport = null;
        if(reportList != null && !reportList.isEmpty()) {
            monthlyReport = reportList.stream()
                    .filter(report -> referenceNo == report.getReference())
                    .findAny()
                    .orElse(null);
        }
        return monthlyReport;
    }
    public static boolean validateEndBalance(MonthlyReport monthlyReport){
        boolean bal = false;
        BigDecimal addition = new BigDecimal(0);
        addition=addition.add(monthlyReport.getStartBalance()).add(monthlyReport.getMutation());
        if(addition.equals(monthlyReport.getEndBalance())){
            bal = true;
        }
        else{
            monthlyReport.setReason("End balance not tally");
        }
        return bal;
    }
}
