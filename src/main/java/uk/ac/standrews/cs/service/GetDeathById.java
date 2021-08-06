package uk.ac.standrews.cs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;

import java.util.*;



@Service
public class GetDeathById {

    @Autowired
    Neo4jServiceImpl neo4jService;
    Map<String, String> detail = new HashMap<>();

    public DeathRecords getDeathByDeathId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH(d:Death) ");
        query.append(getIdAttribute(map));
        query.append(" RETURN ");
        query.append(QuerySetIml.getBirthReturn());
        detail = neo4jService.getPerson(query.toString());
        return new DeathRecords(detail.get("deathDate"), detail.get("age_at_death"), detail.get("Deceased_Identity"), detail.get("death_StorrID"), detail.get("Marital_Status"), detail.get("Death_Place"),
                detail.get("DeathRegistration_Year"), detail.get("death_StandardisedID"), detail.get("Birth_Record_Identity"));
    }

    public BirthRecords getBirthByDeathId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BIRTH_IDENTITY]->(b:Birth) ");
        query.append(getIdAttribute(map));
        query.append(" RETURN ");
        query.append(QuerySetIml.getDeathReturn());
        detail = neo4jService.getPerson(query.toString());
        return new BirthRecords(detail.get("surName"), detail.get("foreName"), detail.get("gender"), detail.get("birthDate"), detail.get("standardised_ID")
                , detail.get("Address"), detail.get("birth_Storr_ID"), detail.get("birth_OriginalID"), detail.get("Changed_foreName"), detail.get("Changed_surName"), detail.get("Child_identity"), detail.get("Father_foreName")
                , detail.get("Father_surName"), detail.get("Father_occupation"), detail.get("Mother_identity"), detail.get("Mother_surName"), detail.get("Mother_foreName"), detail.get("Mother_occupation")
                , detail.get("Marriage_record_identity1"), detail.get("Marriage_record_identity2"), detail.get("Marriage_record_identity3"), detail.get("Adoption"), detail.get("Father_Identity"), detail.get("Death"));

 }

    public List<MarriageRecords> getMarriageByDeathId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String,String> gender= getGender(map);
        map.putAll(gender);
        switch (map.get("gender")) {
            case "M": query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_GROOM_OWN_MARRIAGE_IDENTITY]->(m:Marriage) ");break;
            case "F":query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BRIDE_OWN_MARRIAGE]->(m:Marriage) ");break;
        }
        query.append(getIdAttribute(map));
        query.append(" RETURN ");
        query.append(QuerySetIml.getMarriageReturn());

        return neo4jService.getMarriage(query.toString(), map);
    }

    public Map<String,String> getGender(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death) ");
        query.append(getIdAttribute(map));
        query.append(" RETURN d.SEX AS gender");
        detail = neo4jService.getPerson(query.toString());
        return detail;
    }

    static String getIdAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        QuerySetIml.removeEmptyMap(attribute);
        attribute.forEach((key, value) -> {
            switch (key) {
                case "storr_ID" : query.append(" b.STORR_ID=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
                case "original_ID" : query.append(" b.ORIGINAL_ID=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
                case "standardised_ID" : query.append(" b.STANDARDISED_ID=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
            }
        });
        query.delete(query.length()-3, query.length());
        return query.toString();
    }


}
