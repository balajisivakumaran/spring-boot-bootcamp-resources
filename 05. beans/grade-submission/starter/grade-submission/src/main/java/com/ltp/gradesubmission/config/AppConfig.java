package com.ltp.gradesubmission.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ltp.gradesubmission.GradeSubmissionApplication;

@Configuration
public class AppConfig {

    @Bean
    public Logger logger() {
        return LogManager.getLogger(GradeSubmissionApplication.class);
    }

}

