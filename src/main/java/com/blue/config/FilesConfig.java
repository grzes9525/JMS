package com.blue.config;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


@Configuration
public class FilesConfig {

    @Value("${nov.input.main}")
    public String mainInputFolder;

    @Value("${nov.input.mockData}")
    public String mockInputFolder;

    @Value("${nov.output.requests.all}")
    public String allOutputFolder;

    @Value("${nov.output.requests.failed}")
    public String failedOutputFolder;

    @Value("${nov.logs}")
    public String logFolder;

}
