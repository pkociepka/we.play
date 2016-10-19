package pl.edu.agh.weplay.domain.preferences;

import pl.edu.agh.weplay.domain.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by P on 19.10.2016.
 */
@Entity
@Table(name = "track_played_by_user")
public class TrackPlayedByUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Track track;

    @Column(name = "lastly_played")
    private Date date;
}
