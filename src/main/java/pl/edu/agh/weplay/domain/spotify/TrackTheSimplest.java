package pl.edu.agh.weplay.domain.spotify;

import com.wrapper.spotify.models.SimpleArtist;
import com.wrapper.spotify.models.Track;

/**
 * Created by P on 15.05.2016.
 */
public class TrackTheSimplest {
    private String id;
    private String artists;
    private String name;

    public TrackTheSimplest(Track track) {
        StringBuilder builder = new StringBuilder();
        for (SimpleArtist artist : track.getArtists()) {
            if (builder.length() > 0) {
                builder.append(", ");
                builder.append(artist.getName());
            } else {
                builder.append(artist.getName());
            }
        }
        this.artists = builder.toString();
        this.id = track.getId();
        this.name = track.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TrackTheSimplest{" +
                "id='" + id + '\'' +
                ", artists='" + artists + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
