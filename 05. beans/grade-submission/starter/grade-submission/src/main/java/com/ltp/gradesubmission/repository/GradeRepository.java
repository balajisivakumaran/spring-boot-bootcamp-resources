package com.ltp.gradesubmission.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ltp.gradesubmission.model.Grade;

@Repository
public class GradeRepository {

    private List<Grade> studentGrades = new ArrayList<>();
    private Logger logger;

    public GradeRepository(Logger logger) {
        this.logger = logger;
    }

    public Grade getGrade(int index) {
        logger.debug("Fetching grade at index: {}", index);
        return studentGrades.get(index);
    }

    public void addGrade(Grade grade) {
        logger.info("Adding grade for student: {}, subject: {}", grade.getName(), grade.getSubject());
        studentGrades.add(grade);
    }

    public void updateGrade(Grade grade, int index) {
        logger.info("Updating grade at index: {} for student: {}", index, grade.getName());
        studentGrades.set(index, grade);
    }

    public List<Grade> getGrades() {
        logger.debug("Retrieving all grades (total: {})", studentGrades.size());
        return studentGrades;
    }

}
