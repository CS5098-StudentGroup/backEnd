package uk.ac.standrews.cs.service;

import org.springframework.util.MultiValueMap;
import uk.ac.standrews.cs.Pojo.Person;

import java.util.List;
import java.util.Map;

public interface Neo4jService {
    public StringBuilder printJson(String query) throws Exception;

    public Map<String, String> getPerson(String query);

    public List<Person> getAll(String query);
}
