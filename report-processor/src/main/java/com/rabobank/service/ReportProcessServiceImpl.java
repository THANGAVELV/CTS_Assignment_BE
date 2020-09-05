package com.rabobank.service;

import com.rabobank.constants.FilleConstants;
import com.rabobank.filereader.CSVReader;
import com.rabobank.filereader.FileReaderInterface;
import com.rabobank.filereader.XMLReader;
import com.rabobank.filewriter.CSVWriter;
import com.rabobank.filewriter.FileWriterInterface;
import com.rabobank.pojo.MonthlyReport;
import com.rabobank.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportProcessServiceImpl implements ReportProcessService {
    List<MonthlyReport> failedReportList = new ArrayList<>();

    public void processReport(String filePath, String fileOutputPath) {
        FileReaderInterface fileReader;
        FileWriterInterface fileWriter;
        String extension = FileUtils.findExtension(filePath).get();
        if (extension.equalsIgnoreCase(FilleConstants.CSV_EXTENSION)) {
            fileReader = new CSVReader();
            failedReportList = fileReader.readFile(filePath);
            fileWriter = new CSVWriter();
            fileWriter.writeToFile(failedReportList, fileOutputPath + "/failed-records.csv");
        } else if (extension.equalsIgnoreCase(FilleConstants.XML_EXTENSION)) {
            fileReader = new XMLReader();
            failedReportList = fileReader.readFile(filePath);
        }
        fileWriter = new CSVWriter();
        fileWriter.writeToFile(failedReportList, fileOutputPath + "/failed-records.csv");

    }
}

