package uk.ac.standrews.cs.service;

import org.json.JSONObject;
import org.neo4j.driver.*;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.Pojo.familyTree.Person;
import uk.ac.standrews.cs.neoStorr.util.NeoDbCypherBridge;
import java.util.*;

/**
 * @program: backEnd
 * @description: formatting json data
 * @author: Dongyao Liu
 * @create: 2021-07-25 21:08
 **/

@Service
public class Neo4jServiceImpl implements Neo4jService {

    NeoDbCypherBridge bridge = new NeoDbCypherBridge();
    Session session = bridge.getNewSession();

    //Setting and processing json format
    //return json data
    @Override
    public StringBuilder printJson(String query) throws Exception {
            JSONObject js1 = new JSONObject();
            Result result = session.run(query);
            StringBuilder formatJson = new StringBuilder();
            StringBuilder finalData = new StringBuilder();
            while (result.hasNext()) {
                Record record = result.next();
                List<String> keys = record.keys();
                for (String key : keys) {
                    // keys1.get(i);
                    Value value = record.get(key);
                    String[] getValue = value.toString().split("\"");
                    if (getValue.length == 0) {
                        js1.put(key, "");
                    } else {
                        js1.put(key, getValue[getValue.length-1]);
                    }
                }
                if(!result.hasNext()){formatJson.append(js1);}
                else {formatJson.append(js1).append(",");}
            }
            finalData.append("[").append(formatJson).append("]");
            return finalData;
    }

    @Override
    public Map<String, String> getPerson(String query) throws Exception {
        Map<String, String> getDetails = new HashMap<>();
        Result result = session.run(query);
        while (result.hasNext()) {
            Record record = result.next();
            List<String> keys = record.keys();
            for (String key : keys) {
                // keys1.get(i);
                Value value = record.get(key);
                String[] getValue = value.toString().split("\"");
                if (getValue.length == 0) {
                    getDetails.put(key, "");
                } else {
                    getDetails.put(key, getValue[getValue.length - 1]);
                }
            }
        }

        return getDetails;
    }

    @Override
    public List<Person> getAll(String query, int i) throws Exception {
        List<Person> personList = new ArrayList<>();
        Map<String, String> getDetails = new HashMap<>();
        Result result = session.run(query);
        while (result.hasNext()) {
            Record record = result.next();
            List<String> keys = record.keys();
            for (String key : keys) {
                // keys1.get(i);
                Value value = record.get(key);
                String[] getValue = value.toString().split("\"");
                if (getValue.length == 0) {
                    getDetails.put(key, "");
                } else {
                    getDetails.put(key, getValue[getValue.length - 1]);
                }
            } switch (i) {
                case 4: personList.add(new Person(getDetails.get("Name"), "F", i)); break;
                case 5: personList.add(new Person(getDetails.get("Name"), "M", i)); break;
                default: personList.add(new Person(getDetails.get("Name"), getDetails.get("gender"), i)); break;
            }
        }
        return personList;
    }

    @Override
    public List<MarriageRecords> getMarriage(String query, Map<String, String> map )throws Exception {
        List<MarriageRecords> marriageRecords = new ArrayList<>();
        Map<String, String> getDetails = new HashMap<>();
        Result result = session.run(query);
        while (result.hasNext()) {
            Record record = result.next();
            List<String> keys = record.keys();
            for (String key : keys) {
                // keys1.get(i);
                Value value = record.get(key);
                String[] getValue = value.toString().split("\"");
                if (getValue.length == 0) {
                    getDetails.put(key, "");
                } else {
                    getDetails.put(key, getValue[getValue.length - 1]);
                }
            }
            marriageRecords.add(new MarriageRecords(getDetails.get("SPOUSE_FORENAME"), getDetails.get("SPOUSE_SURNAME"), getDetails.get("SPOUSE_ADDRESS"),getDetails.get("marriageDate"),getDetails.get("MarriagePlace"),getDetails.get("marriage_StandardisedID"),
                    getDetails.get("marriage_StorrID"),getDetails.get("SPOUSE_IDENTITY"),getDetails.get("SPOUSE_OCCUPATION"),getDetails.get("SPOUSE_BIRTH_RECORD_IDENTITY"),getDetails.get("SPOUSE_MARITAL_STATUS"),
                    getDetails.get("MarriageRegistration_Year"),getDetails.get("SPOUSE_FATHER_DECEASED"),getDetails.get("SPOUSE_FATHER_FORENAME"),getDetails.get("SPOUSE_FATHER_SURNAME"),getDetails.get("SPOUSE_FATHER_IDENTITY"),
                    getDetails.get("SPOUSE_FATHER_OCCUPATION"),getDetails.get("SPOUSE_MOTHER_DECEASED"),getDetails.get("SPOUSE_MOTHER_FORENAME"),getDetails.get("SPOUSE_MOTHER_MAIDEN_SURNAME"),getDetails.get("SPOUSE_MOTHER_IDENTITY")));
            }
        return marriageRecords;
        }


    public static StringBuilder linkJson(StringBuilder s1, StringBuilder s2){
        s1.deleteCharAt(s1.length()-1);
        s2.deleteCharAt(0);
        s1.append(",");
        return s1.append(s2);
    }



}
