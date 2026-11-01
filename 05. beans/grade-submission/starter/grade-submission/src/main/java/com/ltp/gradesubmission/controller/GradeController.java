package com.ltp.gradesubmission.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.model.Grade;
import com.ltp.gradesubmission.service.GradeService;

@Controller
public class GradeController {

    GradeService gradeService;
    Logger logger;

    public GradeController(GradeService gradeService, Logger logger) {
        this.gradeService = gradeService;
        this.logger = logger;
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        logger.info("GET / - Rendering grade form (id={})", id);
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        logger.info("POST /handleSubmit - Processing grade submission for student: {}", grade.getName());
        if (result.hasErrors()) {
            logger.warn("Validation errors encountered while submitting grade for student: {}", grade.getName());
            return "form";
        }
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        logger.info("GET /grades - Rendering grades list");
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }

}
