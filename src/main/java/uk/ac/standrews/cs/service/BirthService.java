package uk.ac.standrews.cs.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.standrews.cs.pojo.Birth;
import java.util.List;


@Repository
public interface BirthService {
    List<Birth> findByName (String SURNAME, String FORENAME);
}
