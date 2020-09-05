package com.rabobank.filereader;

import com.rabobank.pojo.MonthlyReport;

import java.util.List;

public interface FileReaderInterface {

    List<MonthlyReport> readFile(String filePath);
}
