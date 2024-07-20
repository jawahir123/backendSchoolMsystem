package com.developers.SchoolManagementSystem.controller;

import com.developers.SchoolManagementSystem.exception.ResourceNotFoundException;
import com.developers.SchoolManagementSystem.model.Exam;
import com.developers.SchoolManagementSystem.repo.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
        private ExamRepository examRepository;
    @GetMapping
    public List<Exam> getAllExam(){
        return this.examRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Exam exam=examRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("exam not found fot id"+id));
        return ResponseEntity.ok().body(exam);

    }
    @PostMapping("/new")
    public Exam createExam( @RequestBody Exam exam){
        return this.examRepository.save(exam);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Exam> updateSubject(@PathVariable(value = "id") Long id,
                                                     @Validated @RequestBody Exam examinationDetails) throws ResourceNotFoundException {
        Exam exam=examRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("subject not found for this id:" + id));
        exam.setMarks(examinationDetails.getMarks());
        return ResponseEntity.ok(this.examRepository.save(exam));

    }
    @DeleteMapping("/delete/{id}")
    public Map< String, Boolean> deleteExam(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Exam exam= examRepository.findById(id).orElseThrow ( () -> new ResourceNotFoundException("subject not found for this id"));
        this.examRepository.delete(exam);
        Map<String ,Boolean> response=new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }



}
