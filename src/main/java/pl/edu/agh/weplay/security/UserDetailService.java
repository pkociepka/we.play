package pl.edu.agh.weplay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.edu.agh.weplay.domain.Authority;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by P on 25.10.2016.
 */
@Component("userDetailsService")
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.findOneByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(login, user.getPassword(), grantedAuthorities);
    }
}
