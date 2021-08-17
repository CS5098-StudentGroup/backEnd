package uk.ac.standrews.cs.service.CommonTool;

import org.json.JSONObject;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
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
    int number = 0;
    int count = 1;

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
                    js1.put(key, getValue[getValue.length - 1]);
                }
            }
            if (!result.hasNext()) {
                formatJson.append(js1);
            } else {
                formatJson.append(js1).append(",");
            }
        }
        finalData.append("[").append(formatJson).append("]");
        return finalData;
    }

    // return the result of query
    // the result stored in hash map
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

    // return the result of query
    // Contains the object that determines what kind of relationship needs to be created.
    // the result will be stored in ArrayList
    @Override
    public List<Person> getAll(String query, int i, String father, String mother, String self) throws Exception {
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
            }
            switch (i) {
                //sibling
                case 3:
                    int s = 0;
                    for (Person person1 : personList) {
                        if (person1.getName().equals(getDetails.get("Name"))) {
                            s++;
                        }
                    }
                    if (getDetails.get("Name").equals(father) || getDetails.get("Name").equals(mother) || s > 0 || self.equals(getDetails.get("Name"))) {
                        personList.add(new Person(getDetails.get("Name") + "(sibling" + count + ")", getDetails.get("gender"), 3));
                        count++;
                    } else {
                        personList.add(new Person(getDetails.get("Name"), getDetails.get("gender"), 3));
                    }
                    break;

                //children
                case 6:
                    int c = 0;
                    for (Person person1 : personList) {
                        if (person1.getName().equals(getDetails.get("Name"))) {
                            c++;
                        }
                    }
                    if (getDetails.get("Name").equals(father) || c > 0) {
                        number += 1;
                        personList.add(new Person(getDetails.get("Name") + "(children" + number + ")", getDetails.get("gender"), 6));
                        System.out.println();
                    } else {
                        personList.add(new Person(getDetails.get("Name"), getDetails.get("gender"), 6));
                    }
                    break;


                case 4:
                    if (getDetails.get("Name").equals(father) || getDetails.get("Name").equals(mother)) {
                        personList.add(new Person(getDetails.get("Name") + "(Bride)", "F", i));
                    } else {
                        personList.add(new Person(getDetails.get("Name"), "F", i));
                    }
                    break;

                case 5:
                    if (getDetails.get("Name").equals(father) || getDetails.get("Name").equals(mother)) {
                        personList.add(new Person(getDetails.get("Name") + "(Groom)", "M", i));
                    } else {
                        personList.add(new Person(getDetails.get("Name"), "M", i));
                    }
                    break;

            }
        }return personList;
    }

    //return person's marriage record
    @Override
    public List<MarriageRecords> getMarriage(String query)throws Exception {
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

    //link two json format strings
    public static StringBuilder linkJson(StringBuilder s1, StringBuilder s2) {
        StringBuilder s = new StringBuilder("[{}]");
        if (s1.toString().equals("[]") && s2.toString().equals("[]")) {
            return s;
        } else if(s1.toString().equals("[]")) {return s2;}
        else if (s2.toString().equals("[]")) {return s1; }
        else{
            s1.deleteCharAt(s1.length() - 1);
            s2.deleteCharAt(0);
            s1.append(",");
            return s1.append(s2);
        }
    }
}
