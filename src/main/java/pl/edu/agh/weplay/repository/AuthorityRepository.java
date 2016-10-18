package pl.edu.agh.weplay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.weplay.domain.Authority;

/**
 * Created by P on 18.10.2016.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
