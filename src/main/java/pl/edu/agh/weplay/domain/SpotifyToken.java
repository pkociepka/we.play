package pl.edu.agh.weplay.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Kurtzz on 2016-12-15.
 */
@Entity
@Table(name = "spotify_token")
public class SpotifyToken implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id @Column(length = 263)
    private String accessToken;

    @Column(name = "expires_in")
    private LocalDateTime expiresIn;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @JsonBackReference
    private User user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDateTime getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(LocalDateTime expiresIn) {
        this.expiresIn = expiresIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotifyToken that = (SpotifyToken) o;

        return accessToken.equals(that.accessToken);
    }

    @Override
    public int hashCode() {
        return accessToken.hashCode();
    }
}
