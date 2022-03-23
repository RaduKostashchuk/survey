package my.survey.repository;

import my.survey.model.SurveyResult;
import my.survey.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SurveyResultRepository extends CrudRepository<SurveyResult, Integer> {
    SurveyResult findBySurveyIdAndUser(int surveyId, User user);

    List<SurveyResult> findByUser(User user);
}
