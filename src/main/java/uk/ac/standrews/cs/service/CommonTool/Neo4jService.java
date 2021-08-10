package uk.ac.standrews.cs.service.CommonTool;

import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.Pojo.familyTree.Person;

import java.util.List;
import java.util.Map;

public interface Neo4jService {
    public StringBuilder printJson(String query) throws Exception;

    public Map<String, String> getPerson(String query) throws Exception;

    public List<Person> getAll(String query, int i, String father, String mother, String self) throws Exception;

    public List<MarriageRecords> getMarriage(String query) throws Exception;

}
