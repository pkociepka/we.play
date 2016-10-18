package pl.edu.agh.weplay.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;

/**
 * Created by P on 18.10.2016.
 */

@Service
public interface UserService {
    User createUser(UserDTO userDTO);
    void changePassword(String password);
    User getUserById(Long id);
    User getUserByLogin(String login);
}
