package pl.edu.agh.weplay.service;

import pl.edu.agh.weplay.domain.User;

/**
 * Created by P on 18.10.2016.
 */
public interface UserService {
    User getUserById(Long id);
    User getUserByLogin(String login);
}
