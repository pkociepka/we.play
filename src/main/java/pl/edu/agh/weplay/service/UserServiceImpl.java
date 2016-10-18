package pl.edu.agh.weplay.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.AuthorityRepository;
import pl.edu.agh.weplay.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by P on 18.10.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    AuthorityRepository authorityRepository;

    public User getUserById(Long id) {
        return userRepository.findByID(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
