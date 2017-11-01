package com.project.homework.service;

import com.project.homework.data.QuestionRepository;
import com.project.homework.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Override
    public Question uploadQuestion(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    @Override
    public void removeQuestion(Long id) {
        questionRepository.delete(id);
    }

    @Override
    public List<Question> findByQuestionContaining(String question) {
        return questionRepository.findByQuestionContaining(question);
    }

    @Override
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }
}
