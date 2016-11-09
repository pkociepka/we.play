package pl.edu.agh.weplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.weplay.domain.User;

import java.util.Optional;

/**
 * Created by P on 18.10.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    @Override
    void delete(User user);
}
