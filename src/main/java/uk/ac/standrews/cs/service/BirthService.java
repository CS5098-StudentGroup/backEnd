package uk.ac.standrews.cs.service;

import uk.ac.standrews.cs.pojo.Birth;
import java.util.List;


public interface BirthService {
    List<Birth> findName (String SURNAME, String FORENAME);
}
