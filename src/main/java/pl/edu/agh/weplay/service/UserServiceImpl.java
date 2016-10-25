package pl.edu.agh.weplay.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.weplay.domain.Authority;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.AuthorityRepository;
import pl.edu.agh.weplay.repository.UserRepository;
import pl.edu.agh.weplay.security.SecurityUtils;
import pl.edu.agh.weplay.service.util.RandomUtil;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
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

    public void changePassword(String password) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            String encryptedPassword = passwordEncoder.encode(password);
            u.setPassword(encryptedPassword);
            userRepository.save(u);
        });
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.findOneByLogin(login).get();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByLogin(login).map(u -> {
            u.getAuthorities().size();
            return u;
        });
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(u -> {
            userRepository.delete(u);
        });
    }
}
