package com.project.homework.service;

import com.project.homework.model.Answer;

import java.util.Map;

public interface AnswerService {
    Map<Long, String> findAnswerByUserId(Long userId);
    Answer saveAnswer(String answer, Long questionId, Long userId);
}
