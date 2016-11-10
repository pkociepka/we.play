package pl.edu.agh.weplay.web.rest.dto;

import pl.edu.agh.weplay.domain.Authority;
import pl.edu.agh.weplay.domain.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by P on 18.10.2016.
 */
public class UserDTO {
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = 8, max = 24)
    private String password;

    private Set<String> authorities;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this(user.getLogin(), null,
                user.getAuthorities().stream()
                        .map(Authority::getName)
                        .collect(Collectors.toSet()));
    }

    public UserDTO(String login, String password, Set<String> authorities) {
        this.login = login;
        this.password = password;
        this.authorities = authorities;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
