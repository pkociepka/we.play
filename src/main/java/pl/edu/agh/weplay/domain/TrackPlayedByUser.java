package pl.edu.agh.weplay.domain;

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

    @Id @Column(name = "track_user_id")
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
