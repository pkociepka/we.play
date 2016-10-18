package pl.edu.agh.weplay.service;

import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;

import java.util.Optional;

/**
 * Created by P on 18.10.2016.
 */

public interface UserService {
    User createUser(UserDTO userDTO);
    void changePassword(String password);
    User getUserById(Long id);
    User getUserByLogin(String login);
    Optional<User> getUserWithAuthoritiesByLogin(String login);
    void deleteUser(String login);
}
