package pl.edu.agh.weplay.domain.preferences;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by P on 19.10.2016.
 */
@Entity
@Table(name = "artist")
public class Artist {

    @Id @Column(name = "id")
    private String id;

}
