package pl.edu.agh.weplay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
public class SpotifyTokenServiceImpl implements SpotifyTokenService {
    private final Logger log = LoggerFactory.getLogger(SpotifyTokenService.class);


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

    @Scheduled(fixedDelay = 5*100*60) //every 5 minutes
    public void removeOldSpotifyTokens() {
        LocalDateTime now = LocalDateTime.now();
        spotifyTokenRepository.findByExpiresInBefore(now).forEach(spotifyToken -> {
            log.info("Scheduler: deleting token: {}", spotifyToken.getAccessToken());
            spotifyTokenRepository.delete(spotifyToken);
        });
    }
}
