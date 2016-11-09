package pl.edu.agh.weplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.weplay.domain.Artist;
import pl.edu.agh.weplay.domain.User;

import java.util.List;

/**
 * Created by P on 25.10.2016.
 */
public interface ArtistRepository extends JpaRepository<Artist, String> {
    List<Artist> findByUsers(User user);
}
