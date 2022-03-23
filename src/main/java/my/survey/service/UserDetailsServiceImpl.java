package my.survey.service;

import my.survey.model.Admin;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AdminService service;

    public UserDetailsServiceImpl(AdminService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = service.findByLogin(username);

        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(admin.getLogin(), admin.getPassword(), Collections.emptyList());
    }
}
