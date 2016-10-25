package pl.edu.agh.weplay.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by P on 19.10.2016.
 */
@Entity
@Table
public class Artist {

    @Id @Column(name = "id")
    private String id;

    @ManyToMany(mappedBy = "artists")
    private Set<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
