package pl.edu.agh.weplay.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.AuthorityRepository;
import pl.edu.agh.weplay.repository.UserRepository;
import pl.edu.agh.weplay.security.AuthoritiesConstants;
import pl.edu.agh.weplay.service.UserService;
import pl.edu.agh.weplay.web.rest.dto.UserDTO;
import pl.edu.agh.weplay.web.rest.util.HeaderUtil;
import pl.edu.agh.weplay.web.rest.util.PaginationUtil;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by P on 18.10.2016.
 */

@RestController
@RequestMapping("/api")
public class UserResource {

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    @Inject
    private UserService userService;

    @RequestMapping(
            value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        if (userRepository.findOneByLogin(userDTO.getLogin()).isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("userManagement", "userexists", "Login already in use"))
                    .body(null);
        } else {
            User newUser = userService.createUser(userDTO);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                    .headers(HeaderUtil.createAlert("userManagement.created", newUser.getLogin()))
                    .body(newUser);
        }
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) throws URISyntaxException {
        Page<User> page = userRepository.findAll(pageable);
        List<UserDTO> managedUserDTOs = page.getContent().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(managedUserDTOs, headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/users/{login:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        return userService.getUserWithAuthoritiesByLogin(login)
                .map(UserDTO::new)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(
            value = "/users/contains/{login:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUserContains(@PathVariable String login) {

        List<UserDTO> userDTOList = new ArrayList<>();
        userService.getUserByLoginIgnoreCaseContaining(login).stream().map(UserDTO::new).forEach(userDTO -> userDTOList.add(userDTO));

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/users/{login:[_'.@a-z0-9-]+}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("userManagement.deleted", login)).build();
    }
}
