package pl.edu.agh.weplay.service;

import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;

import java.util.Optional;

/**
 * Created by P on 18.10.2016.
 */

public interface UserService {
    User createUser(UserDTO userDTO);
    User createUserInformation(String login, String password);
    void updateUserInformation();
    public void deleteUserInformation(String login);
    void changePassword(String password);
    User getUserById(Long id);
    User getUserByLogin(String login);
    Optional<User> getUserWithAuthoritiesByLogin(String login);
    User getUserWithAuthorities();
    void deleteUser(String login);
}
