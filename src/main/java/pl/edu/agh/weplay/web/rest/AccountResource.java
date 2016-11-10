package pl.edu.agh.weplay.web.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.weplay.domain.Token;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.TokenRepository;
import pl.edu.agh.weplay.repository.UserRepository;
import pl.edu.agh.weplay.security.SecurityUtils;
import pl.edu.agh.weplay.service.UserService;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by P on 25.10.2016.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private TokenRepository tokenRepository;

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> registerAccount(@Valid @RequestBody UserDTO userDTO, HttpServletRequest request) {

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        return userRepository.findOneByLogin(userDTO.getLogin())
                .map(user -> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
                .orElseGet(() -> {
                    User user = userService.createUserInformation(userDTO.getLogin(), userDTO.getPassword());
                    String baseUrl = request.getScheme() + // "http"
                            "://" +                                // "://"
                            request.getServerName() +              // "myhost"
                            ":" +                                  // ":"
                            request.getServerPort() +              // "80"
                            request.getContextPath();              // "/myContextPath" or "" if deployed in root context

                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }

    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }

    @RequestMapping(
            value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccount() {
        return Optional.ofNullable(userService.getUserWithAuthorities())
                .map(user -> new ResponseEntity<>(new UserDTO(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(
            value = "/account",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveAccount(@RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findOneByLogin(userDTO.getLogin());
        /*if (existingUser.isPresent() && !existingUser.get().getLogin().equalsIgnoreCase(userDTO.getLogin())) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("user-management", "emailexists", "Email already in use")).body(null);
        }*/
        return userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin())
                .map(user -> {
                    userService.updateUserInformation();
                    return new ResponseEntity<String>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(
            value = "/account/change_password",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> changePassword(@RequestBody String password) {
        if (!checkPasswordLength(password)) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.BAD_REQUEST);
        }
        userService.changePassword(password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/account/session",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Token>> getCurrentSessions() {
        return userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin())
                .map(user -> new ResponseEntity<>(tokenRepository.findByUser(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private boolean checkPasswordLength(String password) {
        return (!StringUtils.isEmpty(password) &&
                password.length() >= UserDTO.PASSWORD_MIN_LENGTH &&
                password.length() <= UserDTO.PASSWORD_MAX_LENGTH);
    }
}
