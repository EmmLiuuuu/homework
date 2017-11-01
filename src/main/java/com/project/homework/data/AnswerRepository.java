package com.project.homework.data;

import com.project.homework.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findAnswerByUserIdAndQuestionId(Long userId, Long questionId);
    List<Answer> findAnswersByUserId(Long userId);
}
