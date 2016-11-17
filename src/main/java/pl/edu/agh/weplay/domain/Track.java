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

    @Column
    private String name;

    @Column //[0, 1.0]
    private float danceability;

    @Column //[0, 1.0]
    private float energy;

    @Column //[0, 1.0]
    private float instrumentalness;

    @Column //[0, 1.0]
    private float valence;

    @ManyToMany(mappedBy = "tracks")
    private Set<User> users;

    @ManyToMany(mappedBy = "tracks")
    private Set<Artist> artists;

    @ManyToMany
    @JoinTable(
            name = "genre_by_track",
            joinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private Set<Genre> genres = new HashSet<>();

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

    public float getDanceability() {
        return danceability;
    }

    public void setDanceability(float danceability) {
        this.danceability = danceability;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(float instrumentalness) {
        this.instrumentalness = instrumentalness;
    }

    public float getValence() {
        return valence;
    }

    public void setValence(float valence) {
        this.valence = valence;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
