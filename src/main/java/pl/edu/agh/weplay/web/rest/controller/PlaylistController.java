package pl.edu.agh.weplay.web.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.weplay.domain.playlist.PlaylistPreferences;

/**
 * Created by Roksana on 2016-10-18.
 */
@RestController
public class PlaylistController {

    @RequestMapping(value = "preferences", method = RequestMethod.POST)
    public ResponseEntity receivePreferences(@RequestBody PlaylistPreferences preferences) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
