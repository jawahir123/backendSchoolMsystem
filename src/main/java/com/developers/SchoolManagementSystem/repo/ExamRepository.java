package com.developers.SchoolManagementSystem.repo;

import com.developers.SchoolManagementSystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
