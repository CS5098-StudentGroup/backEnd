package uk.ac.standrews.cs.service;

import org.springframework.stereotype.Service;
import java.util.*;
/**
 * @program: backEnd
 * @description: Processing dynamic query statements
 * @author: Dongyao Liu
 * @create: 2021-07-26 21:25
 **/
@Service
public class QuerySetIml implements QuerySet {


    //Get cypher and formatting it
    //get data when user input deathDate or birthDate
    @Override
    public String getBirthDeathQuery(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BIRTH_IDENTITY]->(b:Birth) ");
        query.append(getAttribute(map));
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, b.BIRTH_DAY+'/'+b.BIRTH_MONTH+'/'+b.BIRTH_YEAR AS birthDate," +
                "d.DEATH_DAY+'/'+d.DEATH_MONTH+'/'+d.DEATH_YEAR AS deathDate, b.STANDARDISED_ID AS standardised_ID, b.BIRTH_ADDRESS AS Address, " +
                "b.STORR_ID AS Storr_ID, b.ORIGINAL_ID AS Original_ID, b.CHANGED_FORENAME AS Changed_foreName, b.CHANGED_SURNAME AS Changed_surName," +
                "b.CHILD_IDENTITY AS Child_identity, b.FATHER_FORENAME AS Father_foreName, b.FATHER_SURNAME AS Father_surName," +
                "b.FATHER_OCCUPATION AS Father_occupation, b.MOTHER_IDENTITY AS Mother_identity, b.MOTHER_SURNAME AS Mother_surName, b.MOTHER_FORENAME AS Mother_foreName," +
                "b.MOTHER_OCCUPATION AS Mother_occupation, b.MARRIAGE_RECORD_IDENTITY1 AS Marriage_record_identity1, b.MARRIAGE_RECORD_IDENTITY2 AS Marriage_record_identity2," +
                "b.MARRIAGE_RECORD_IDENTITY3 AS Marriage_record_identity3");
        return query.toString();
    }

    //query when marriage record contains "bride"
    @Override
    public String getBirthBrideQuery(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage)");
        query.append(getAttribute(map));
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, " +
                "b.BIRTH_DAY+'/'+b.BIRTH_MONTH+'/'+b.BIRTH_YEAR AS birthDate, " +
                "m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, b.STANDARDISED_ID AS standardised_ID");
        /*System.out.println("bride"+query);*/
        return query.toString();
    }

    //query when marriage record contains "groom"
    @Override
    public String getBirthGroomQuery(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m:Marriage)");
        query.append(getAttribute(map));
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, " +
                "b.BIRTH_DAY+'/'+b.BIRTH_MONTH+'/'+b.BIRTH_YEAR AS birthDate, " +
                "m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate");
        /*System.out.println("groom"+query);*/
        return query.toString();
    }

    public String getGroomQuery(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_GROOM_IDENTITY]->(m:Marriage)");
        query.append(getAttribute(map));
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, " +
                "m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.BRIDE_AGE_OR_DATE_OF_BIRTH " +
                "AS BirthDate of Bride, m.GROOM_AGE_OR_DATE_OF_BIRTH AS BirthDate of Groom");
        return query.toString();
    }

    @Override
    public String getBrideQuery(Map<String, String> map) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)-[r:GROUND_TRUTH_BIRTH_BRIDE_IDENTITY]->(m:Marriage)");
        query.append(getAttribute(map));
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, " +
                "m.MARRIAGE_DAY+'-'+m.MARRIAGE_MONTH+'-'+m.MARRIAGE_YEAR AS marriageDate, m.BRIDE_AGE_OR_DATE_OF_BIRTH " +
                "AS BirthDate_of_Bride, m.GROOM_AGE_OR_DATE_OF_BIRTH AS BirthDate_of_Groom");
        /*System.out.println("bride"+query);*/
        return query.toString();
    }

    //Setting the attribute format in cypher
    private static String getAttribute(Map<String, String> attribute) {
        StringBuilder query = new StringBuilder();
        query.append(" WHERE");
        removeEmptyMap(attribute);
        attribute.forEach((key, value) -> {
            switch (key) {
                case "surName" : query.append(" b.SURNAME=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
                case "foreName" : query.append(" b.FORENAME=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
                case "sex" : query.append(" b.SEX=").append('"').append(value.toUpperCase()).append('"').append(" AND");break;
                case "birth_Day" : query.append(" b.BIRTH_DAY=").append('"').append(value).append('"').append(" AND");break;
                case "birth_Month" : query.append(" b.BIRTH_MONTH=").append('"').append(value).append('"').append(" AND");break;
                case "birth_Year" : query.append(" b.BIRTH_YEAR=").append('"').append(value).append('"').append(" AND");break;
                case "death_Day" : query.append(" d.DEATH_DAY=").append('"').append(value).append('"').append(" AND");break;
                case "death_Month" : query.append(" d.DEATH_MONTH=").append('"').append(value).append('"').append(" AND");break;
                case "death_Year" : query.append(" d.DEATH_YEAR=").append('"').append(value).append('"').append(" AND");break;
                case "marriage_Day" : query.append(" m.MARRIAGE_DAY=").append('"').append(value).append('"').append(" AND");break;
                case "marriage_Month" : query.append(" m.MARRIAGE_MONTH=").append('"').append(value).append('"').append(" AND");break;
                case "marriage_Year" : query.append(" m.MARRIAGE_YEAR=").append('"').append(value).append('"').append(" AND");break;
                case "standardised_ID" : query.append(" b.STANDARDISED_ID").append('"').append(value).append('"').append(" AND");break;
                default: query.append(" AND");
            }
        });
        query.delete(query.length()-3, query.length());
        return query.toString();
    }



    //remove empty value stored in Map
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

    //processing the gender from front-end
    //set the sex that can be identified by cypher
    public static String setSex(String sex){
        switch (sex) {
            case "male":
                sex = "M";
                break;
            case "female":
                sex = "F";
                break;
            default:
                sex = null;
                break;
        }
        return sex;
    }


    public static String[] splitBirth(String dateOfBirth) {
        String[] birth = new String[3];
        if (!dateOfBirth.equals("null")) {
            birth = dateOfBirth.split("-");
        } else {
            birth[0] = birth[1] = birth[2] = null;
        }
        return birth;
    }

    public static String[] splitDeath(String dateOfDeath) {
        String[] death = new String[3];
        if (!dateOfDeath.equals("null")) {
            death = dateOfDeath.split("-");
        } else {
            death[0] = death[1] = death[2] = null;
        }
        return death;
    }

    public static String[] splitMarriage(String dateOfMarriage) {
        String[] marriage = new String[3];
        if (!dateOfMarriage.equals("null")) {
            marriage = dateOfMarriage.split("-");
        } else {
            marriage[0] = marriage[1] = marriage[2] = null;
        }
        return marriage;
    }


}