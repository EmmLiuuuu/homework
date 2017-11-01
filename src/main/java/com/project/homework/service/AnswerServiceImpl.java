package com.project.homework.service;

import com.project.homework.data.AnswerRepository;
import com.project.homework.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Map<Long, String> findAnswerByUserId(Long userId) {
        List<Answer> answerList = answerRepository.findAnswersByUserId(userId);
        Map<Long, String> answerMap = new HashMap<>();
        for(Answer answer : answerList){
            answerMap.put(answer.getQuestionId(), answer.getAnswer());
        }
        return answerMap;
    }

    @Override
    public Answer saveAnswer(String answer, Long questionId, Long userId) {
        Answer answer1 = answerRepository.findAnswerByUserIdAndQuestionId(userId, questionId);
        if(answer1 != null){
            answer1.setAnswer(answer);
            return answerRepository.saveAndFlush(answer1);
        }else{
            Answer newAnswer = new Answer(answer, questionId, userId);
            return answerRepository.saveAndFlush(newAnswer);
        }
    }
}
