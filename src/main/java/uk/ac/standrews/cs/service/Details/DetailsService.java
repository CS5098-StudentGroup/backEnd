package uk.ac.standrews.cs.service.Details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.service.CommonTool.Neo4jService;
import java.util.*;


/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-08-05 11:44
 **/

@Service
public class DetailsService {
    @Autowired
    Neo4jService neo4jService;
    Map<String, String> detail = new HashMap<>();

    public BirthRecords getBirthRecords(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth) ");
        query.append(getAttribute(map));
        query.append(" RETURN ");
        query.append(getBirthReturn());
        detail = neo4jService.getPerson(query.toString());
        return new BirthRecords(detail.get("surName"), detail.get("foreName"), detail.get("gender"), detail.get("birthDate"), detail.get("standardised_ID")
                , detail.get("Address"), detail.get("birth_Storr_ID"), detail.get("birth_OriginalID"), detail.get("Changed_foreName"), detail.get("Changed_surName"), detail.get("Child_identity"), detail.get("Father_foreName")
                , detail.get("Father_surName"), detail.get("Father_occupation"), detail.get("Mother_identity"), detail.get("Mother_surName"), detail.get("Mother_foreName"), detail.get("Mother_occupation")
                , detail.get("Marriage_record_identity1"), detail.get("Marriage_record_identity2"), detail.get("Marriage_record_identity3"), detail.get("Adoption"), detail.get("Father_Identity"), detail.get("Death"));
    }

    public DeathRecords getDeathRecords(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BIRTH_IDENTITY]->(b:Birth) ");
        query.append(getAttribute(map));
        query.append(" RETURN ");
        query.append(getDeathReturn());
        detail = neo4jService.getPerson(query.toString());
        return new DeathRecords(detail.get("deathDate"), detail.get("age_at_death"), detail.get("Deceased_Identity"), detail.get("death_StorrID"), detail.get("Marital_Status"), detail.get("Death_Place"),
                detail.get("DeathRegistration_Year"), detail.get("death_StandardisedID"), detail.get("Birth_Record_Identity"));
    }

    public List<MarriageRecords> getMarriageRecords(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        switch (map.get("gender")) {
            case "M": query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m:Marriage) ");break;
            case "F":query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage) ");break;
        }
        query.append(getAttribute(map));
        query.append(" RETURN ");
        query.append(getMarriageReturn(map));
        return neo4jService.getMarriage(query.toString());
    }




    public static String getBirthReturn() {

        return  "b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, b.BIRTH_DAY+'/'+b.BIRTH_MONTH+'/'+b.BIRTH_YEAR AS birthDate," +
                "b.STANDARDISED_ID AS standardised_ID, b.BIRTH_ADDRESS AS Address,b.STORR_ID AS birth_Storr_ID," +
                "b.ORIGINAL_ID AS birth_OriginalID, b.CHANGED_FORENAME AS Changed_foreName, b.CHANGED_SURNAME AS Changed_surName," +
                "b.CHILD_IDENTITY AS Child_identity, b.FATHER_FORENAME AS Father_foreName, b.FATHER_SURNAME AS Father_surName," +
                "b.FATHER_OCCUPATION AS Father_occupation, b.MOTHER_IDENTITY AS Mother_identity, b.MOTHER_SURNAME AS Mother_surName, b.MOTHER_FORENAME AS Mother_foreName," +
                "b.MOTHER_OCCUPATION AS Mother_occupation, b.MARRIAGE_RECORD_IDENTITY1 AS Marriage_record_identity1, b.MARRIAGE_RECORD_IDENTITY2 AS Marriage_record_identity2," +
                "b.MARRIAGE_RECORD_IDENTITY3 AS Marriage_record_identity3, b.ADOPTION AS Adoption, b.FATHER_IDENTITY AS Father_Identity, b.DEATH AS Death";

    }

    public static String getDeathReturn() {

        return  "d.DEATH_DAY+'/'+d.DEATH_MONTH+'/'+d.DEATH_YEAR AS deathDate, d.AGE_AT_DEATH AS age_at_death, d.DECEASED_IDENTITY AS Deceased_Identity, d.STORR_ID AS death_StorrID," +
                "d.MARITAL_STATUS AS Marital_Status, d.PLACE_OF_DEATH AS Death_Place, d.YEAR_OF_REGISTRATION AS DeathRegistration_Year, d.STANDARDISED_ID AS death_StandardisedID, d.BIRTH_RECORD_IDENTITY AS Birth_Record_Identity";

    }

    private static String getMarriageReturn(Map<String, String> map) {
        StringBuilder r = new StringBuilder();
        r.append("m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.PLACE_OF_MARRIAGE AS MarriagePlace, m.STANDARDISED_ID AS marriage_StandardisedID," +
                "m.STORR_ID AS marriage_StorrID, m.YEAR_OF_REGISTRATION AS MarriageRegistration_Year");
        if(map.get("gender").equals("M")) {r.append(", m.BRIDE_ADDRESS AS SPOUSE_ADDRESS, m.BRIDE_SURNAME AS SPOUSE_SURNAME, m.BRIDE_FORENAME AS SPOUSE_FORENAME, m.BRIDE_IDENTITY AS SPOUSE_IDENTITY," +
                "m.BRIDE_OCCUPATION AS SPOUSE_OCCUPATION, m.BRIDE_BIRTH_RECORD_IDENTITY AS SPOUSE_BIRTH_RECORD_IDENTITY, m.BRIDE_MARITAL_STATUS AS SPOUSE_MARITAL_STATUS, m.BRIDE_FATHER_DECEASED AS SPOUSE_FATHER_DECEASED, " +
                "m.BRIDE_FATHER_FORENAME AS SPOUSE_FATHER_FORENAME, m.BRIDE_FATHER_SURNAME AS SPOUSE_FATHER_SURNAME, m.BRIDE_FATHER_IDENTITY AS SPOUSE_FATHER_IDENTITY, m.BRIDE_FATHER_OCCUPATION AS SPOUSE_FATHER_OCCUPATION," +
                "m.BRIDE_MOTHER_DECEASED AS SPOUSE_MOTHER_DECEASED, m.BRIDE_MOTHER_FORENAME AS SPOUSE_MOTHER_FORENAME, m.BRIDE_MOTHER_MAIDEN_SURNAME AS SPOUSE_MOTHER_MAIDEN_SURNAME, m.BRIDE_MOTHER_IDENTITY AS SPOUSE_MOTHER_IDENTITY");}

        if(map.get("gender").equals("F")) {r.append(", m.GROOM_ADDRESS AS SPOUSE_ADDRESS, m.GROOM_SURNAME AS SPOUSE_SURNAME, m.GROOM_FORENAME AS SPOUSE_FORENAME, m.GROOM_IDENTITY AS SPOUSE_IDENTITY," +
                "m.GROOM_OCCUPATION AS SPOUSE_OCCUPATION, m.GROOM_BIRTH_RECORD_IDENTITY AS SPOUSE_BIRTH_RECORD_IDENTITY, m.GROOM_MARITAL_STATUS AS SPOUSE_MARITAL_STATUS,  m.GROOM_FATHER_DECEASED AS SPOUSE_FATHER_DECEASED," +
                "m.GROOM_FATHER_FORENAME AS SPOUSE_FATHER_FORENAME, m.GROOM_FATHER_SURNAME AS SPOUSE_FATHER_SURNAME, m.GROOM_FATHER_IDENTITY AS SPOUSE_FATHER_IDENTITY, m.GROOM_FATHER_OCCUPATION AS SPOUSE_FATHER_OCCUPATION, " +
                "m.GROOM_MOTHER_DECEASED AS SPOUSE_MOTHER_DECEASED, m.GROOM_MOTHER_FORENAME AS SPOUSE_MOTHER_FORENAME, m.GROOM_MOTHER_MAIDEN_SURNAME AS SPOUSE_MOTHER_MAIDEN_SURNAME, m.GROOM_MOTHER_IDENTITY AS SPOUSE_MOTHER_IDENTITY");}
        return r.toString();
    }


    private static void removeEmptyMap(Map<String, String> maps) {
        Set<String> set = maps.keySet();
        Iterator<String> it = set.iterator();
        List<String> listKey = new ArrayList<>();
        while (it.hasNext()) {
            String str = it.next();
            if (maps.get(str) == null || "".equals(maps.get(str))) {
                listKey.add(str);
            }
        }
        for (String key : listKey) {
            maps.remove(key);
        }
    }


    private static String getAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        removeEmptyMap(attribute);
        attribute.forEach((key, value) -> {
            switch (key) {
                case "gender" : query.append(" b.SEX=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
                case "standardised_ID" : query.append(" b.STANDARDISED_ID=").append('"').append(value).append('"').append(" AND");break;
                case "death" : query.append(" b.DEATH=").append('"').append(value).append('"').append(" AND");break;
            }
        });
        query.delete(query.length()-3, query.length());
        return query.toString();
    }



}
