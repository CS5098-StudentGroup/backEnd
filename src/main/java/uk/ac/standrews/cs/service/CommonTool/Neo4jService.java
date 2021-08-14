package uk.ac.standrews.cs.service.CommonTool;

import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.Pojo.familyTree.Person;

import java.util.List;
import java.util.Map;

public interface Neo4jService {
    StringBuilder printJson(String query) throws Exception;

    Map<String, String> getPerson(String query) throws Exception;

    List<Person> getAll(String query, int i, String father, String mother, String self) throws Exception;

    List<MarriageRecords> getMarriage(String query) throws Exception;

}
