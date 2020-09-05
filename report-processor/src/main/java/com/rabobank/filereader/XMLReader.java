package com.rabobank.filereader;

import com.rabobank.pojo.MonthlyReport;
import com.rabobank.pojo.xml.Record;
import com.rabobank.pojo.xml.Records;
import com.rabobank.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class XMLReader implements FileReaderInterface {

    @Override
    public List<MonthlyReport> readFile(final String filePath) {
        List<MonthlyReport> reportList = new ArrayList<>();
        List<MonthlyReport> failedReportList = new ArrayList<>();
        File file = new File(filePath);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Records.class, Record.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Records records = (Records) jaxbUnmarshaller.unmarshal(file);
            List<Record> recordList = records.getRecords();
            for (Record record : recordList) {
                MonthlyReport monthlyReport = new MonthlyReport();
                long referenceNo = record.getReference();
                monthlyReport.setReference(referenceNo);
                monthlyReport.setAccountNumber(record.getAccountNumber());
                monthlyReport.setDescription(record.getDescription());
                monthlyReport.setStartBalance(new BigDecimal(record.getStartBalance()));
                monthlyReport.setMutation(new BigDecimal(record.getMutation()));
                monthlyReport.setEndBalance(new BigDecimal(record.getEndBalance()));
                if (FileUtils.findReference(reportList, referenceNo) == null) {
                    if (FileUtils.validateEndBalance(monthlyReport)) {
                        reportList.add(monthlyReport);
                    } else {
                        failedReportList.add(monthlyReport);
                    }

                } else {
                    failedReportList.add(monthlyReport);
                }

            }
        } catch (JAXBException e) {
            log.error("Error in parsing");
        }
        return failedReportList;
    }

}
