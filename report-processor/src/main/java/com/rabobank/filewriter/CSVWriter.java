package com.rabobank.filewriter;

import com.rabobank.pojo.MonthlyReport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CSVWriter implements FileWriterInterface{
    @Override
    public void writeToFile(final List<MonthlyReport> records, final String path) {

        try (
                FileWriter fileWriter = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Reference", "Description","Reason"));
        ) {
            for (MonthlyReport monthlyReport:records) {
                csvPrinter.printRecord(monthlyReport.getReference(), monthlyReport.getDescription(),monthlyReport.getReason());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            log.error("Error in creating file");
        }
    }
    }

