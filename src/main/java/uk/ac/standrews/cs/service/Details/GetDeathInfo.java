package uk.ac.standrews.cs.service.Details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.familyTree.Person;
import uk.ac.standrews.cs.service.CommonTool.Neo4jService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-08-10 20:16
 **/


//This class is mainly for those people who only has information in death record
@Service
public class GetDeathInfo {

    @Autowired
    Neo4jService neo4jService;
    Map<String, String> detail = new HashMap<>();

    //return and create self pojo
    public Person getSelf(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death) ");
        query.append("WHERE d.STANDARDISED_ID=").append('"').append(map.get("death_standardised_ID")).append('"');
        query.append(" AND d.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN d.FORENAME+'-'+d.SURNAME AS Name, d.SEX AS gender");
        detail = neo4jService.getPerson(query.toString());
        return new Person(detail.get("Name"), detail.get("gender"), 0 );
    }

    //return and create bride pojo
    public List<Person> getBride(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BRIDE_OWN_MARRIAGE]->(m:Marriage) ");
        query.append("WHERE d.STANDARDISED_ID=").append('"').append(map.get("death_standardised_ID")).append('"');
        query.append(" AND d.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.BRIDE_FORENAME+'-'+m.BRIDE_SURNAME AS Name");
        return neo4jService.getAll(query.toString(), 4, null, null, null);
    }

    //return and create groom pojo
    public List<Person> getGroom(Map<String,String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_GROOM_OWN_MARRIAGE]->(m:Marriage) ");
        query.append("WHERE d.STANDARDISED_ID=").append('"').append(map.get("death_standardised_ID")).append('"');
        query.append(" AND d.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN m.GROOM_FORENAME+'-'+m.GROOM_SURNAME AS Name");
        return neo4jService.getAll(query.toString(), 5, null, null, null);
    }

    //return and create sibling pojo
    public List<Person> getSiblings(Map<String ,String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String self = getSelf(map).getName();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_SIBLING_LINKAGE]->(d1:Death) ");
        query.append("WHERE d.STANDARDISED_ID=").append('"').append(map.get("death_standardised_ID")).append('"');
        query.append(" AND d.SEX=").append('"').append(map.get("gender")).append('"');
        query.append(" RETURN d1.FORENAME+'-'+d1.SURNAME AS Name, d1.SEX AS gender");
        System.out.println(query);
        return neo4jService.getAll(query.toString(),3, null, null ,self);
    }


}
