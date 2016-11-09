package pl.edu.agh.weplay.domain;

import pl.edu.agh.weplay.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by P on 19.10.2016.
 */
@Entity
@Table
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @Column(name = "id")
    private String id;

    @ManyToMany(mappedBy = "genres")
    private Set<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
