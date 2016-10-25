package pl.edu.agh.weplay.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by P on 19.10.2016.
 */
@Entity
@Table
public class Track implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @Column(name = "id")
    private String id;

    @ManyToMany
    @JoinTable(
            name = "track_artist",
            joinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id", referencedColumnName = "id")}
    )
    private Set<Artist> artists= new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "track_genre",
            joinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private Set<Genre> genres = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
}
