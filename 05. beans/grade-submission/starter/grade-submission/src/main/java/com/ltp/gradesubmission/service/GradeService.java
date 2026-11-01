package com.ltp.gradesubmission.service;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.constants.Constants;
import com.ltp.gradesubmission.model.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

@Service
public class GradeService {

    GradeRepository gradeRepository;
    Logger logger;

    public GradeService(GradeRepository gradeRepository, Logger logger) {
        this.gradeRepository = gradeRepository;
        this.logger = logger;
    }

    public Grade getGrade(int index) {
        return gradeRepository.getGrade(index);
    }

    public void addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
    }

    public void updateGrade(Grade grade, int index) {
        gradeRepository.updateGrade(grade, index);
    }

    public List<Grade> getGrades() {
        return gradeRepository.getGrades();
    }

    public int getGradeIndex(String id) {
        logger.debug("Searching for grade index with id: {}", id);
        for (int i = 0; i < getGrades().size(); i++) {
            if (getGrade(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGradeById(String id) {
        int index = getGradeIndex(id);
        if (index == Constants.NOT_FOUND) {
            logger.debug("Grade id '{}' not found; initializing a new Grade instance", id);
            return new Grade();
        }
        logger.debug("Found existing grade for id '{}' at index {}", id, index);
        return getGrade(index);
    }

    public void submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            logger.info("Submitting new grade with id: {}", grade.getId());
            addGrade(grade);
        } else {
            logger.info("Updating existing grade with id: {} at index: {}", grade.getId(), index);
            updateGrade(grade, index);
        }
    }
}
