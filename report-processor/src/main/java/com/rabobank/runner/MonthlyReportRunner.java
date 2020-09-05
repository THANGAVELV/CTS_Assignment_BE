package com.rabobank.runner;

import com.rabobank.service.ReportProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

import static java.lang.System.exit;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan( {"com.rabobank"})
public class MonthlyReportRunner implements CommandLineRunner {

    @Autowired
    ReportProcessService reportProcessService;
    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(MonthlyReportRunner.class);
        app.run(args);
        app.setBannerMode(Banner.Mode.OFF);
    }

    @Override
    public void run(final String... args) throws Exception {
        if (args.length == 2) {
            reportProcessService.processReport(args[0], args[1]);
        } else {
            log.error("Need argument 1 as input full file path and argument 2 output full folder path");
        }

        exit(0);
    }
}
