package uk.ac.standrews.cs.service.Details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.familyTree.Person;
import uk.ac.standrews.cs.service.CommonTool.Neo4jService;
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
    public Person getSelf(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN b.FORENAME+'-'+b.SURNAME AS Name, b.SEX AS gender");
        detail = neo4jService.getPerson(query.toString());
        return new Person(detail.get("Name"), detail.get("gender"), 0 );
    }

    public Person getFather(Map<String, String> map) throws Exception {
        String selfName = getSelf(map).getName();
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_PARENTS_MARRIAGE]->(m:Marriage) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.GROOM_FORENAME+'-'+m.GROOM_SURNAME AS Name");
        detail = neo4jService.getPerson(query.toString());
        detail.put("gender","M");
        if(selfName.equals(detail.get("Name"))) {
            return new Person(detail.get("Name") + "-(father)", detail.get("gender"), 1);
        }
        else {
            return new Person(detail.get("Name"), detail.get("gender"), 1);
        }
    }

    public Person getMother(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String selfName = getSelf(map).getName();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_MOTHER_IDENTITY]->(b1:Birth) ");
        query.append("WHERE b1.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN b.FORENAME+'-'+b.SURNAME AS Name, b.SEX AS gender");
        detail = neo4jService.getPerson(query.toString());
        if(selfName.equals(detail.get("Name"))) {
            return new Person(detail.get("Name") + "-(mother)", detail.get("gender"), 2);
        }
        else {
            return new Person(detail.get("Name"), detail.get("gender"), 2);
        }
    }

    public List<Person> getBride(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String father = getFather(map).getName();
        String mother = getMother(map).getName();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m:Marriage) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.BRIDE_FORENAME+'-'+m.BRIDE_SURNAME AS Name");

        return neo4jService.getAll(query.toString(), 4, father, mother, null);
    }

    public List<Person> getGroom(Map<String,String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String father = getFather(map).getName();
        String mother = getMother(map).getName();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.GROOM_FORENAME+'-'+m.GROOM_SURNAME AS Name");
        return neo4jService.getAll(query.toString(), 5, father, mother, null);
    }

    public List<Person> getSiblings(Map<String ,String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String father = getFather(map).getName();
        String mother = getMother(map).getName();
        String self = getSelf(map).getName();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_SIBLING_LINKAGE]->(b1:Birth) ");
        query.append("WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN b1.FORENAME+'-'+b1.SURNAME AS Name, b1.SEX AS gender");
        return neo4jService.getAll(query.toString(),3, father, mother ,self);
    }

    public List<Person> getChildren(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String self = getSelf(map).getName();
        String gender = getSelf(map).getGender();
        switch (gender) {
            case "M": query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_FATHER_GROOM_IDENTITY]->(m:Marriage) MATCH(c:Birth)-[a:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m) ");
            query.append(" WHERE c.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');break;
            case "F": query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_MOTHER_IDENTITY]->(b1:Birth)");
                query.append(" WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
                query.append(" AND b.SEX=").append('"').append(map.get("gender")).append('"');break;
        }
        query.append("RETURN b.FORENAME+'-'+b.SURNAME AS Name, b.SEX AS gender");
        return neo4jService.getAll(query.toString(),6, self, null, null);
    }




}
