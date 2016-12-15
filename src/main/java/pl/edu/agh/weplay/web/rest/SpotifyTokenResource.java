package pl.edu.agh.weplay.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.weplay.domain.SpotifyToken;
import pl.edu.agh.weplay.service.SpotifyTokenService;
import pl.edu.agh.weplay.service.UserService;
import pl.edu.agh.weplay.web.rest.util.HeaderUtil;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Kurtzz on 2016-12-15.
 */
@RestController
@RequestMapping("/api")
public class SpotifyTokenResource {

    @Inject
    private SpotifyTokenService spotifyTokenService;

    @Inject
    private UserService userService;

    @RequestMapping(
            value = "/spotifytoken/{access_token}/{expires_in}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSpotifyToken(
            @PathVariable String access_token,
            @PathVariable String expires_in
    ) throws URISyntaxException {
        SpotifyToken newSpotifyToken = spotifyTokenService.createSpotifyToken(
                access_token,
                Integer.parseInt(expires_in),
                userService.getUserWithAuthorities()
        );

        return ResponseEntity.created(new URI("/api/spotifytoken/" + newSpotifyToken.getAccessToken()))
                .headers(HeaderUtil.createAlert("spotifyToken.createad", newSpotifyToken.getAccessToken()))
                .body(newSpotifyToken);
    }

    @RequestMapping(
            value = "/spotifytoken/{login}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpotifyToken> getSpotifyToken(@PathVariable String login) {
        SpotifyToken spotifyToken = spotifyTokenService.getSpotifyTokenByLogin(login);
        if (spotifyToken != null) {
            return new ResponseEntity<>(spotifyToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
