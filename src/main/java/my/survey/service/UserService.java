package my.survey.service;

import my.survey.model.User;
import my.survey.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public User save(User user) {
        return users.save(user);
    }

    public User findByExternalId(String externalId) {
        return users.findByExternalId(externalId);
    }
}
