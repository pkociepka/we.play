package pl.edu.agh.weplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.weplay.domain.SpotifyToken;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Kurtzz on 2016-12-15.
 */
public interface SpotifyTokenRepository extends JpaRepository<SpotifyToken, String> {
    SpotifyToken findOneByUser_login(String login);
    List<SpotifyToken> findByExpiresInBefore(LocalDateTime localDateTime);
}
