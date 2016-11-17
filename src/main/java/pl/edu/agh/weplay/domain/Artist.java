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
public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @Column(name = "id")
    private String id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "artists")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "artist_by_track",
            joinColumns = {@JoinColumn(name = "artist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")}
    )
    private Set<Track> tracks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "genre_by_artist",
            joinColumns = {@JoinColumn(name = "artist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "related_artists",
            joinColumns = {@JoinColumn(name = "artist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "related_id", referencedColumnName = "id")})
    private Set<Artist> relatedArtists = new HashSet<>();

    @ManyToMany(mappedBy = "relatedArtists")
    private Set<Artist> artists = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Artist> getRelatedArtists() {
        return relatedArtists;
    }

    public void setRelatedArtists(Set<Artist> relatedArtists) {
        this.relatedArtists = relatedArtists;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
}
