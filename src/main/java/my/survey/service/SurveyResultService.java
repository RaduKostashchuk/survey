package my.survey.service;

import my.survey.model.SurveyResult;
import my.survey.model.User;
import my.survey.repository.SurveyResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SurveyResultService {
    private final SurveyService surveys;
    private final UserService users;
    private final SurveyResultRepository results;

    public SurveyResultService(SurveyService surveys, UserService users, SurveyResultRepository results) {
        this.surveys = surveys;
        this.users = users;
        this.results = results;
    }

    public Iterable<SurveyResult> findAll() {
        return results.findAll();
    }

    public List<SurveyResult> findByUserId(String id) {
        User user = users.findByExternalId(id);
        if (user == null) {
            throw new NoSuchElementException("Такого пользователя не существует");
        }
        return results.findByUser(user);
    }

    public void begin(SurveyResult result) {
        String externalId = result.getUser().getExternalId();
        int surveyId = result.getSurveyId();
        User user = users.findByExternalId(externalId);
        surveys.findById(surveyId);
        if (user == null) {
            user = User.of(externalId);
        } else {
            user.getResults().stream()
                    .filter(r -> r.getSurveyId() == surveyId)
                    .findFirst().ifPresent(user::deleteResult);
        }
        result.setStartDate(LocalDateTime.now());
        user.addResult(result);
        users.save(user);
    }

    public void end(SurveyResult result) {
        String externalId = result.getUser().getExternalId();
        int surveyId = result.getSurveyId();
        User user = users.findByExternalId(externalId);
        SurveyResult persisted = results.findBySurveyIdAndUser(surveyId, user);
        if (persisted == null) {
            throw new NoSuchElementException("Опрос не был начат");
        }
        if (persisted.getEndDate() != null) {
            throw new IllegalArgumentException("Опрос уже пройден этим пользователем");
        }
        persisted.setEndDate(LocalDateTime.now());
        result.getAnswers().forEach(persisted::addAnswer);
        results.save(persisted);
    }

}
