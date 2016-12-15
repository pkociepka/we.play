package pl.edu.agh.weplay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.weplay.domain.SpotifyToken;
import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.repository.SpotifyTokenRepository;

import java.time.LocalDateTime;

/**
 * Created by Kurtzz on 2016-12-15.
 */
@Service
@Transactional
public class SpotifyTokenServiceImpl implements SpotifyTokenService{
    @Autowired
    private SpotifyTokenRepository spotifyTokenRepository;

    public SpotifyToken createSpotifyToken(String accessToken, int expiresIn, User user) {
        SpotifyToken spotifyToken = new SpotifyToken();
        spotifyToken.setAccessToken(accessToken);
        spotifyToken.setExpiresIn(LocalDateTime.now().plusSeconds(expiresIn));
        spotifyToken.setUser(user);

        spotifyTokenRepository.save(spotifyToken);
        return spotifyToken;
    }

    public SpotifyToken getSpotifyTokenByLogin(String login) {
        return spotifyTokenRepository.findOneByUser_login(login);
    }
}
