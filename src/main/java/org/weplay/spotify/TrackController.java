package org.weplay.spotify;

import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.weplay.spotify.SpotifyConfig.*;

@Controller
@RequestMapping(value = "/tracks")
public class TrackController {

    @RequestMapping(value = "/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrackTheSimplest>> findTrack(@PathVariable String title) {
        TrackSearchRequest request = API.searchTracks(title).build();
        List<TrackTheSimplest> list = new ArrayList<>();
        try {
            Page<Track> trackSearchResult = request.get();
            trackSearchResult.getItems().stream().forEach(track -> list.add(new TrackTheSimplest(track)));

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WebApiException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
