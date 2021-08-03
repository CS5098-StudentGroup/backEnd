package uk.ac.standrews.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.Person;
import java.util.*;

/**
 * @program: backEnd
 * @description: get relationships(father, mother etc)
 * @author: Dongyao Liu
 * @create: 2021-08-01 22:03
 **/
@Service
public class GetInfo {
    @Autowired
    Neo4jService neo4jService;
    Map<String, String> detail = new HashMap<>();
    public Person getSelf(Map<String, String> map){
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN b.FORENAME+'-'+b.SURNAME AS Name, b.SEX AS gender");
        /*System.out.println(query.toString());*/
        detail = neo4jService.getPerson(query.toString());
        return new Person(detail.get("Name"), detail.get("gender"), 0 );
    }

    public Person getFather(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_PARENTS_MARRIAGE]->(m:Marriage) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.GROOM_FORENAME+'-'+m.GROOM_SURNAME AS Name");
        detail = neo4jService.getPerson(query.toString());
        detail.put("gender","M");
        return new Person(detail.get("Name"), detail.get("gender"), 1 );
    }

    public Person getMother(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_MOTHER_IDENTITY]->(b1:Birth) ");
        query.append("WHERE b1.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN b.FORENAME+'-'+b.SURNAME AS Name, b.SEX AS gender");
        detail = neo4jService.getPerson(query.toString());
        return new Person(detail.get("Name"), detail.get("gender"), 2);
    }

    public Person getBride(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m:Marriage) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.BRIDE_FORENAME+'-'+m.BRIDE_SURNAME AS Name");
        detail = neo4jService.getPerson(query.toString());
        detail.put("gender","F");
        return new Person(detail.get("Name"), detail.get("gender"), 4);
    }

    public Person getGroom(Map<String,String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.GROOM_FORENAME+'-'+m.GROOM_SURNAME AS Name");
        detail = neo4jService.getPerson(query.toString());
        detail.put("gender","M");
        return new Person(detail.get("Name"), detail.get("gender"), 5);
    }

    public Person getSiblings(Map<String ,String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_SIBLING_LINKAGE]->(b1:Birth) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN b1.FORENAME+'-'+b1.SURNAME AS Name, b1.SEX AS gender");
        detail = neo4jService.getPerson(query.toString());
        return new Person(detail.get("Name"), detail.get("gender"), 3);
    }
}
