package my.survey.repository;

import my.survey.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByExternalId(String id);
}
