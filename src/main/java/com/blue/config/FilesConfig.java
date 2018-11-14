package com.blue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


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
