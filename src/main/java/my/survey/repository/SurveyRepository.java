package my.survey.repository;

import my.survey.model.Survey;
import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    boolean existsByName(String name);
}
