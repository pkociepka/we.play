package pl.edu.agh.weplay.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.agh.weplay.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by P on 18.10.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    List<User> findOneByLoginIgnoreCaseContaining(String login);

    @Query(value = "select distinct user from User user left join fetch user.authorities",
            countQuery = "select count(user) from User user")
    Page<User> findAllWithAuthorities(Pageable pageable);

    @Override
    void delete(User user);
}
