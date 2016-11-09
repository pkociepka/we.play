package pl.edu.agh.weplay.repository;

import pl.edu.agh.weplay.domain.User;
import pl.edu.agh.weplay.domain.Track;

import java.util.List;

/**
 * Created by P on 25.10.2016.
 */
public interface TrackRepository {
    List<Track> findByUsers(User user);
}
