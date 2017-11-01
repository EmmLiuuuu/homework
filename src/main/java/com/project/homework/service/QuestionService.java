package com.project.homework.service;

import com.project.homework.model.Question;

import java.util.List;

public interface QuestionService {

    Question uploadQuestion(Question question);

    void removeQuestion(Long id);

    List<Question> findByQuestionContaining(String question);

    void addQuestion(Question question);
}
