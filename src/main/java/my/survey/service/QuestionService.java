package my.survey.service;

import my.survey.model.Question;
import my.survey.model.QuestionType;
import my.survey.model.Survey;
import my.survey.repository.QuestionRepository;
import my.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class QuestionService {
    private final QuestionRepository questions;
    private final SurveyRepository surveys;

    public QuestionService(QuestionRepository questions, SurveyRepository surveys) {
        this.questions = questions;
        this.surveys = surveys;
    }

    public Question save(int surveyId, Question question) {
        Survey survey = surveys.findById(surveyId).orElse(null);
        if (survey == null) {
            throw new NoSuchElementException("Такого опроса не существует");
        }
        question.setSurvey(survey);
        return questions.save(question);
    }

    public void delete(int id) {
        if (!questions.existsById(id)) {
            throw new NoSuchElementException("Такого вопроса не существует");
        }
        questions.deleteById(id);
    }

    public void update(int surveyId, Question question) {
        String text = question.getText();
        QuestionType type =  question.getType();
        Question persisted = questions.findById(question.getId()).orElse(null);
        if (persisted == null) {
            throw new NoSuchElementException("Такого вопроса не существует");
        }
        if (!surveys.existsById(surveyId)) {
            throw new NoSuchElementException("Такого опроса не существует");
        }
        if (text != null) {
            persisted.setText(text);
        }
        if (type != null) {
            persisted.setType(type);
        }
        questions.save(persisted);
    }

}
