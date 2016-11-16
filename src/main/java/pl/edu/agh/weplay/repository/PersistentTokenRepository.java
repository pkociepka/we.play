package pl.edu.agh.weplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.weplay.domain.PersistentToken;
import pl.edu.agh.weplay.domain.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by P on 25.10.2016.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {
    List<PersistentToken> findByUser(User user);
    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);
}
