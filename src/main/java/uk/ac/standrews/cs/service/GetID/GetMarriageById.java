package uk.ac.standrews.cs.service.GetID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.service.CommonTool.Neo4jServiceImpl;
import uk.ac.standrews.cs.service.Search.QuerySetIml;

import java.util.*;


@Service
public class GetMarriageById {

    @Autowired
    Neo4jServiceImpl neo4jService;
    Map<String, String> detail = new HashMap<>();

    public DeathRecords getDeathByMarriageGroomId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String, String> groom = getGroomBrideIdentity(map);
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_GROOM_OWN_MARRIAGE_IDENTITY]-(m:Marriage) ");
        query.append(getIdAttribute(map));
        query.append(" RETURN ");
        query.append(QuerySetIml.getDeathReturn());
        detail = neo4jService.getPerson(query.toString());
        return new DeathRecords(detail.get("deathDate"), detail.get("age_at_death"), detail.get("Deceased_Identity"), detail.get("death_StorrID"), detail.get("Marital_Status"), detail.get("Death_Place"),
                detail.get("DeathRegistration_Year"), detail.get("death_StandardisedID"), detail.get("Birth_Record_Identity"));
    }

    public DeathRecords getDeathByMarriageBrideId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String, String> groom = getGroomBrideIdentity(map);
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BRIDE_OWN_MARRIAGE]-(m:Marriage) ");
        query.append(getIdAttribute(map));
        query.append(" RETURN ");
        query.append(QuerySetIml.getDeathReturn());
        detail = neo4jService.getPerson(query.toString());
        return new DeathRecords(detail.get("deathDate"), detail.get("age_at_death"), detail.get("Deceased_Identity"), detail.get("death_StorrID"), detail.get("Marital_Status"), detail.get("Death_Place"),
                detail.get("DeathRegistration_Year"), detail.get("death_StandardisedID"), detail.get("Birth_Record_Identity"));
    }

    public BirthRecords getBirthByMarriageGroomId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String, String> groom = getGroomBrideIdentity(map);
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]-(m:Marriage) ");
        query.append(getGroomAttribute(groom));
        query.append(" RETURN ");
        query.append(QuerySetIml.getBirthReturn());
        detail = neo4jService.getPerson(query.toString());
        return new BirthRecords(detail.get("surName"), detail.get("foreName"), detail.get("gender"), detail.get("birthDate"), detail.get("standardised_ID")
                , detail.get("Address"), detail.get("birth_Storr_ID"), detail.get("birth_OriginalID"), detail.get("Changed_foreName"), detail.get("Changed_surName"), detail.get("Child_identity"), detail.get("Father_foreName")
                , detail.get("Father_surName"), detail.get("Father_occupation"), detail.get("Mother_identity"), detail.get("Mother_surName"), detail.get("Mother_foreName"), detail.get("Mother_occupation")
                , detail.get("Marriage_record_identity1"), detail.get("Marriage_record_identity2"), detail.get("Marriage_record_identity3"), detail.get("Adoption"), detail.get("Father_Identity"), detail.get("Death"));

    }

    public BirthRecords getBirthByMarriageBrideId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String, String> brideIdentity = getGroomBrideIdentity(map);
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]-(m:Marriage) ");
        query.append(getBrideAttribute(brideIdentity));
        query.append(" RETURN ");
        query.append(QuerySetIml.getBirthReturn());
        detail = neo4jService.getPerson(query.toString());
        return new BirthRecords(detail.get("surName"), detail.get("foreName"), detail.get("gender"), detail.get("birthDate"), detail.get("standardised_ID")
                , detail.get("Address"), detail.get("birth_Storr_ID"), detail.get("birth_OriginalID"), detail.get("Changed_foreName"), detail.get("Changed_surName"), detail.get("Child_identity"), detail.get("Father_foreName")
                , detail.get("Father_surName"), detail.get("Father_occupation"), detail.get("Mother_identity"), detail.get("Mother_surName"), detail.get("Mother_foreName"), detail.get("Mother_occupation")
                , detail.get("Marriage_record_identity1"), detail.get("Marriage_record_identity2"), detail.get("Marriage_record_identity3"), detail.get("Adoption"), detail.get("Father_Identity"), detail.get("Death"));

    }

    public List<MarriageRecords> getMarriageByMarriageGroomId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String, String> groomBrideIdentity = getGroomBrideIdentity(map);
        query.append("MATCH (m:Marriage) ");
        query.append(getIdAttribute(groomBrideIdentity));
        query.append(" RETURN ");
        query.append(getMarriageGroomReturn(groomBrideIdentity));
        return neo4jService.getMarriage(query.toString());

    }

    public List<MarriageRecords> getMarriageByMarriageBrideId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String, String> brideBrideIdentity = getGroomBrideIdentity(map);
        query.append("MATCH (m:Marriage) ");
        query.append(getIdAttribute(brideBrideIdentity));
        query.append(" RETURN ");
        query.append(getMarriageBrideReturn(brideBrideIdentity));
        detail = neo4jService.getPerson(query.toString());
        return neo4jService.getMarriage(query.toString());
    }

    public Map<String, String> getGroomBrideIdentity(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (m:Marriage) ");
        query.append(getIdAttribute(map));
        query.append(" RETURN m.GROOM_IDENTITY AS groom_identity, m.BRIDE_IDENTITY AS bride_identity");
        detail = neo4jService.getPerson(query.toString());
        map.putAll(detail);
        return map;
    }

    static String getIdAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        QuerySetIml.removeEmptyMap(attribute);
        attribute.forEach((key, value) -> {
            switch (key) {
                case "storr_ID":
                    query.append(" m.STORR_ID=").append('"').append(value.toUpperCase()).append('"').append(" AND");
                    break;
                case "original_ID":
                    query.append(" m.ORIGINAL_ID=").append('"').append(value.toUpperCase()).append('"').append(" AND");
                    break;
                case "standardised_ID":
                    query.append(" m.STANDARDISED_ID=").append('"').append(value.toUpperCase()).append('"').append(" AND");
                    break;
            }
        });
        query.delete(query.length() - 3, query.length());
        return query.toString();
    }

    static String getGroomAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        QuerySetIml.removeEmptyMap(attribute);
        attribute.forEach((key, value) -> {
            if (key.equals("groom_identity")) {
                query.append(" m.GROOM_IDENTITY=").append('"').append(value.toUpperCase()).append('"').append(" AND");
            }
        });
        query.delete(query.length() - 3, query.length());
        return query.toString();
    }

    static String getBrideAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        QuerySetIml.removeEmptyMap(attribute);
        attribute.forEach((key, value) -> {
            if (key.equals("bride_identity")) {
                query.append(" m.BRIDE_IDENTITY=").append('"').append(value.toUpperCase()).append('"').append(" AND");
            }
        });
        query.delete(query.length() - 3, query.length());
        return query.toString();
    }

    static String getMarriageGroomReturn(Map<String, String> map) {
        StringBuilder r = new StringBuilder();
        r.append("m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.PLACE_OF_MARRIAGE AS MarriagePlace, m.STANDARDISED_ID AS marriage_StandardisedID," +
                "m.STORR_ID AS marriage_StorrID, m.YEAR_OF_REGISTRATION AS MarriageRegistration_Year");

        r.append(", m.GROOM_ADDRESS AS SPOUSE_ADDRESS, m.GROOM_SURNAME AS SPOUSE_SURNAME, m.GROOM_FORENAME AS SPOUSE_FORENAME, m.GROOM_IDENTITY AS SPOUSE_IDENTITY," +
                "m.GROOM_OCCUPATION AS SPOUSE_OCCUPATION, m.GROOM_BIRTH_RECORD_IDENTITY AS SPOUSE_BIRTH_RECORD_IDENTITY, m.GROOM_MARITAL_STATUS AS SPOUSE_MARITAL_STATUS,  m.GROOM_FATHER_DECEASED AS SPOUSE_FATHER_DECEASED," +
                "m.GROOM_FATHER_FORENAME AS SPOUSE_FATHER_FORENAME, m.GROOM_FATHER_SURNAME AS SPOUSE_FATHER_SURNAME, m.GROOM_FATHER_IDENTITY AS SPOUSE_FATHER_IDENTITY, m.GROOM_FATHER_OCCUPATION AS SPOUSE_FATHER_OCCUPATION, " +
                "m.GROOM_MOTHER_DECEASED AS SPOUSE_MOTHER_DECEASED, m.GROOM_MOTHER_FORENAME AS SPOUSE_MOTHER_FORENAME, m.GROOM_MOTHER_MAIDEN_SURNAME AS SPOUSE_MOTHER_MAIDEN_SURNAME, m.GROOM_MOTHER_IDENTITY AS SPOUSE_MOTHER_IDENTITY");
        return r.toString();
    }

    static String getMarriageBrideReturn(Map<String, String> map) {
        StringBuilder r = new StringBuilder();
        r.append("m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.PLACE_OF_MARRIAGE AS MarriagePlace, m.STANDARDISED_ID AS marriage_StandardisedID," +
                "m.STORR_ID AS marriage_StorrID, m.YEAR_OF_REGISTRATION AS MarriageRegistration_Year");

        r.append(", m.BRIDE_ADDRESS AS SPOUSE_ADDRESS, m.BRIDE_SURNAME AS SPOUSE_SURNAME, m.BRIDE_FORENAME AS SPOUSE_FORENAME, m.BRIDE_IDENTITY AS SPOUSE_IDENTITY," +
                "m.BRIDE_OCCUPATION AS SPOUSE_OCCUPATION, m.BRIDE_BIRTH_RECORD_IDENTITY AS SPOUSE_BIRTH_RECORD_IDENTITY, m.BRIDE_MARITAL_STATUS AS SPOUSE_MARITAL_STATUS, m.BRIDE_FATHER_DECEASED AS SPOUSE_FATHER_DECEASED, " +
                "m.BRIDE_FATHER_FORENAME AS SPOUSE_FATHER_FORENAME, m.BRIDE_FATHER_SURNAME AS SPOUSE_FATHER_SURNAME, m.BRIDE_FATHER_IDENTITY AS SPOUSE_FATHER_IDENTITY, m.BRIDE_FATHER_OCCUPATION AS SPOUSE_FATHER_OCCUPATION," +
                "m.BRIDE_MOTHER_DECEASED AS SPOUSE_MOTHER_DECEASED, m.BRIDE_MOTHER_FORENAME AS SPOUSE_MOTHER_FORENAME, m.BRIDE_MOTHER_MAIDEN_SURNAME AS SPOUSE_MOTHER_MAIDEN_SURNAME, m.BRIDE_MOTHER_IDENTITY AS SPOUSE_MOTHER_IDENTITY");

        return r.toString();
    }

}
