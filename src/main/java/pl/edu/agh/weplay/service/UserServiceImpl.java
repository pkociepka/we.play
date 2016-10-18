package pl.edu.agh.weplay.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.weplay.domain.Authority;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.AuthorityRepository;
import pl.edu.agh.weplay.repository.UserRepository;
import pl.edu.agh.weplay.service.util.RandomUtil;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

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

    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());

        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = new HashSet<>();
            userDTO.getAuthorities().stream().forEach(
                    authority -> authorities.add(authorityRepository.findOne(authority))
            );
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findByID(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
