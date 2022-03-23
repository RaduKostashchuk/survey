package my.survey.service;

import my.survey.model.Admin;
import my.survey.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository admins;

    public AdminService(AdminRepository admins) {
        this.admins = admins;
    }

    public Admin findByLogin(String login) {
        return admins.findByLogin(login);
    }
}
