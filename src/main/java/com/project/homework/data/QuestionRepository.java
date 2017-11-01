package com.project.homework.data;

import com.project.homework.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(Long id);
    Question findByAnswerId(Long answerId);
    List<Question> findByQuestionContaining(String question);
}
