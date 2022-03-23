package my.survey.service;

import my.survey.model.Survey;
import my.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SurveyService {
    private final SurveyRepository surveys;

    public SurveyService(SurveyRepository surveys) {
        this.surveys = surveys;
    }

    public Survey save(Survey survey) {
        if (surveys.existsByName(survey.getName())) {
            throw new IllegalArgumentException("Такой опрос уже существует");
        }
        return surveys.save(survey);
    }

    public void delete(int id) {
        if (!surveys.existsById(id)) {
            throw new NoSuchElementException("Такого опроса не существует");
        }
        surveys.deleteById(id);
    }

    public void update(Survey survey) {
        String name = survey.getName();
        String description = survey.getDescription();
        Survey persisted = surveys.findById(survey.getId()).orElse(null);
        if (persisted == null) {
            throw new NoSuchElementException("Такого опроса не существует");
        }
        if (name != null) {
            persisted.setName(name);
        }
        if (description != null) {
            persisted.setDescription(description);
        }
        surveys.save(persisted);
    }

    public Iterable<Survey> findAll() {
        return surveys.findAll();
    }

    public Survey findById(int id) {
        Survey result = surveys.findById(id).orElse(null);
        if (result == null) {
            throw new NoSuchElementException("Такого опроса не существует");
        }
        return result;
    }
}
