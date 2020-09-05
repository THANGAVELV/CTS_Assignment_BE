package com.rabobank.filereader;

import com.rabobank.pojo.MonthlyReport;
import com.rabobank.utils.FileUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements FileReaderInterface {

    @Override
    public List<MonthlyReport> readFile(final String filePath) {
        List<MonthlyReport> reportList = new ArrayList<>();
        List<MonthlyReport> failedReportList = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                MonthlyReport monthlyReport = new MonthlyReport();
                if(csvRecord.get("Reference") != null && !csvRecord.get("Reference").isEmpty()) {
                    long referenceNo = Long.valueOf(csvRecord.get("Reference"));
                    monthlyReport.setReference(referenceNo);
                    monthlyReport.setAccountNumber(csvRecord.get("AccountNumber"));
                    monthlyReport.setDescription(csvRecord.get("Description"));
                    monthlyReport.setStartBalance(new BigDecimal(csvRecord.get("Start Balance")));
                    monthlyReport.setMutation(new BigDecimal(csvRecord.get("Mutation")));
                    monthlyReport.setEndBalance(new BigDecimal(csvRecord.get("End Balance")));
                    if(FileUtils.findReference(reportList,referenceNo) == null) {
                        if(FileUtils.validateEndBalance(monthlyReport)){
                            reportList.add(monthlyReport);
                        }
                        else{
                            failedReportList.add(monthlyReport);
                        }

                    }
                    else{
                        failedReportList.add(monthlyReport);
                    }
                }
            }
        }
        catch (
                IOException e
                ){
            return null;
        }
        return failedReportList;
    }}

