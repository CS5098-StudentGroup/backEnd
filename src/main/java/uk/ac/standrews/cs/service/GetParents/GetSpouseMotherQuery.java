package uk.ac.standrews.cs.service.GetParents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.service.Details.DetailsService;
import uk.ac.standrews.cs.service.CommonTool.Neo4jService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: backEnd
 * @description: get parents details query
 * @author: Dongyao Liu
 * @create: 2021-08-06 19:42
 **/

@Service
public class GetSpouseMotherQuery {
    @Autowired
    Neo4jService neo4jService;
    Map<String, String> detail = new HashMap<>();

    public Map<String,String> getGender(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth) ");
        query.append(" WHERE b.STANDARDISED_ID=").append('"').append(map.get("standardised_ID")).append('"');
        query.append(" RETURN b.SEX AS gender");
        System.out.println(query);
        detail = neo4jService.getPerson(query.toString());
        map.putAll(detail);
        System.out.println(map.get("standardised_ID"));
        return map;
    }

    public Map<String,String> getSpouseMarriageId(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String,String> gender = getGender(map);

        switch (gender.get("gender")) {
            case "M": query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage) ");break;
            case "F":query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m:Marriage) ");break;
        }
        query.append(" WHERE m.STANDARDISED_ID=").append('"').append(gender.get("marriage_standardised_ID")).append('"');
        query.append(" RETURN ");
        query.append("b.STANDARDISED_ID AS Spouse_Marriage_id");
        detail=neo4jService.getPerson(query.toString());
        System.out.println(detail);
        map.putAll(detail);
        return map;
    }

    public String getMotherID(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        Map<String,String> spouseMarriageId = getSpouseMarriageId(map);
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_MOTHER_IDENTITY]->(child:Birth) ");
        query.append(getAttribute(spouseMarriageId));
        query.append(" RETURN b.STANDARDISED_ID AS mother_standardised_ID");
        detail = neo4jService.getPerson(query.toString());
        return detail.get("mother_standardised_ID");
    }
    public BirthRecords getBirthRecords(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        String mID=getMotherID(map);
        query.append("MATCH (b:Birth) ");
        query.append(" WHERE");
        query.append(" b.STANDARDISED_ID=").append('"').append(mID).append('"');
        query.append(" RETURN ");
        query.append(DetailsService.getBirthReturn());
        detail = neo4jService.getPerson(query.toString());
        return new BirthRecords(detail.get("surName"), detail.get("foreName"), detail.get("gender"), detail.get("birthDate"), detail.get("standardised_ID")
                , detail.get("Address"), detail.get("birth_Storr_ID"), detail.get("birth_OriginalID"), detail.get("Changed_foreName"), detail.get("Changed_surName"), detail.get("Child_identity"), detail.get("Father_foreName")
                , detail.get("Father_surName"), detail.get("Father_occupation"), detail.get("Mother_identity"), detail.get("Mother_surName"), detail.get("Mother_foreName"), detail.get("Mother_occupation")
                , detail.get("Marriage_record_identity1"), detail.get("Marriage_record_identity2"), detail.get("Marriage_record_identity3"), detail.get("Adoption"), detail.get("Father_Identity"), detail.get("Death"));
    }

    public DeathRecords getDeathRecords(Map<String, String> map) throws Exception {
        String mID = getMotherID(map);
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BIRTH_IDENTITY]->(b:Birth) ");
        query.append(" WHERE b.STANDARDISED_ID=").append('"').append(mID).append('"');
        query.append(" RETURN ");
        query.append(DetailsService.getDeathReturn());
        detail = neo4jService.getPerson(query.toString());
        return new DeathRecords(detail.get("deathDate"), detail.get("age_at_death"), detail.get("Deceased_Identity"), detail.get("death_StorrID"), detail.get("Marital_Status"), detail.get("Death_Place"),
                detail.get("DeathRegistration_Year"), detail.get("death_StandardisedID"), detail.get("Birth_Record_Identity"));
    }

    public List<MarriageRecords> getMarriageRecords(Map<String, String> map) throws Exception {
        String mID = getMotherID(map);
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage) ");
        query.append(" WHERE b.STANDARDISED_ID=").append('"').append(mID).append('"');
        query.append(" RETURN ");
        query.append(getMarriageReturn());
        System.out.println(query);
        return neo4jService.getMarriage(query.toString());
    }

    private static String getAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        query.append(" child.STANDARDISED_ID=").append('"').append(attribute.get("Spouse_Marriage_id")).append('"');
        return query.toString();
    }

    //    private static String getMarriageReturn() {
//        return "m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.PLACE_OF_MARRIAGE AS MarriagePlace, m.STANDARDISED_ID AS marriage_StandardisedID," +
//                "m.STORR_ID AS marriage_StorrID, m.YEAR_OF_REGISTRATION AS MarriageRegistration_Year" +
//                ", m.BRIDE_ADDRESS AS SPOUSE_ADDRESS, m.BRIDE_SURNAME AS SPOUSE_SURNAME, m.BRIDE_FORENAME AS SPOUSE_FORENAME, m.BRIDE_IDENTITY AS SPOUSE_IDENTITY," +
//                "m.BRIDE_OCCUPATION AS SPOUSE_OCCUPATION, m.BRIDE_BIRTH_RECORD_IDENTITY AS SPOUSE_BIRTH_RECORD_IDENTITY, m.BRIDE_MARITAL_STATUS AS SPOUSE_MARITAL_STATUS, m.BRIDE_FATHER_DECEASED AS SPOUSE_FATHER_DECEASED, " +
//                "m.BRIDE_FATHER_FORENAME AS SPOUSE_FATHER_FORENAME, m.BRIDE_FATHER_SURNAME AS SPOUSE_FATHER_SURNAME, m.BRIDE_FATHER_IDENTITY AS SPOUSE_FATHER_IDENTITY, m.BRIDE_FATHER_OCCUPATION AS SPOUSE_FATHER_OCCUPATION," +
//                "m.BRIDE_MOTHER_DECEASED AS SPOUSE_MOTHER_DECEASED, m.BRIDE_MOTHER_FORENAME AS SPOUSE_MOTHER_FORENAME, m.BRIDE_MOTHER_MAIDEN_SURNAME AS SPOUSE_MOTHER_MAIDEN_SURNAME, m.BRIDE_MOTHER_IDENTITY AS SPOUSE_MOTHER_IDENTITY";
//
//    }
    private static String getMarriageReturn() {
        return "m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.PLACE_OF_MARRIAGE AS MarriagePlace, m.STANDARDISED_ID AS marriage_StandardisedID," +
                "m.STORR_ID AS marriage_StorrID, m.YEAR_OF_REGISTRATION AS MarriageRegistration_Year" +
                ", m.GROOM_ADDRESS AS SPOUSE_ADDRESS, m.GROOM_SURNAME AS SPOUSE_SURNAME, m.GROOM_FORENAME AS SPOUSE_FORENAME, m.GROOM_IDENTITY AS SPOUSE_IDENTITY," +
                "m.GROOM_OCCUPATION AS SPOUSE_OCCUPATION, m.GROOM_BIRTH_RECORD_IDENTITY AS SPOUSE_BIRTH_RECORD_IDENTITY, m.GROOM_MARITAL_STATUS AS SPOUSE_MARITAL_STATUS,  m.GROOM_FATHER_DECEASED AS SPOUSE_FATHER_DECEASED," +
                "m.GROOM_FATHER_FORENAME AS SPOUSE_FATHER_FORENAME, m.GROOM_FATHER_SURNAME AS SPOUSE_FATHER_SURNAME, m.GROOM_FATHER_IDENTITY AS SPOUSE_FATHER_IDENTITY, m.GROOM_FATHER_OCCUPATION AS SPOUSE_FATHER_OCCUPATION, " +
                "m.GROOM_MOTHER_DECEASED AS SPOUSE_MOTHER_DECEASED, m.GROOM_MOTHER_FORENAME AS SPOUSE_MOTHER_FORENAME, m.GROOM_MOTHER_MAIDEN_SURNAME AS SPOUSE_MOTHER_MAIDEN_SURNAME, m.GROOM_MOTHER_IDENTITY AS SPOUSE_MOTHER_IDENTITY";
    }
}
