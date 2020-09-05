package com.rabobank.filewriter;

import com.rabobank.pojo.MonthlyReport;

import java.util.List;

public interface FileWriterInterface {
    void writeToFile(List<MonthlyReport> records,String path);
}
