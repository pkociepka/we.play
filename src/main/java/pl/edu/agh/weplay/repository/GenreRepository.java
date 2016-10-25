package pl.edu.agh.weplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.domain.Genre;

import java.util.List;

/**
 * Created by P on 25.10.2016.
 */
public interface GenreRepository extends JpaRepository<Genre, String> {
    List<Genre> findByUsers(User user);
}
