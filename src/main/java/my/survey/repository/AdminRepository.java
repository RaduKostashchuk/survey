package my.survey.repository;

import my.survey.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findByLogin(String login);
}
