package pl.edu.agh.weplay.service;

import pl.edu.agh.weplay.domain.SpotifyToken;
import pl.edu.agh.weplay.domain.User;

/**
 * Created by Kurtzz on 2016-12-15.
 */
public interface SpotifyTokenService {
    SpotifyToken createSpotifyToken(String accessToken, int expiresIn, User user);
    SpotifyToken getSpotifyTokenByLogin(String login);
}
