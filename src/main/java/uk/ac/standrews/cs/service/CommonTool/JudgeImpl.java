package uk.ac.standrews.cs.service.CommonTool;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import uk.ac.standrews.cs.service.Search.QuerySet;
import uk.ac.standrews.cs.service.Search.QuerySetIml;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-30 23:00
 **/
@Data
@Service
public class JudgeImpl implements Judge {

    @Autowired
    QuerySet querySet;
    @Autowired
    Neo4jService neo4jService;
    @Autowired
    Judge judge;
    @Autowired
    FamilyTree familyTree = new FamilyTree();
    @Autowired
    PersonalDetails personalDetails = new PersonalDetails();

    ObjectMapper objectMapper = new ObjectMapper();
    StringBuilder groomCypher;
    StringBuilder brideCypher;
    StringBuilder finalJson = new StringBuilder();
    public static final String EMPTY = "empty";
    Map<String, String> valueMap = new HashMap<>();
    @Override
    public StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception {
        groomCypher = neo4jService.printJson(querySet.getBirthGroomQuery(valueMap));
        brideCypher = neo4jService.printJson(querySet.getBirthBrideQuery(valueMap));
        if(valueMap.get("death").equals(EMPTY)) {
            //not die
            //birth + marriage(switch:gender)
            if(QuerySetIml.marriageRecordIsEmpty(groomCypher, brideCypher)){
                finalJson = neo4jService.printJson(querySet.addPeopleNotDie(valueMap));
            }
            else {
                //not die married birth
                switch (valueMap.get("gender")) {
                    case "M": finalJson = neo4jService.printJson(querySet.getDetailOfBride(valueMap)); break;
                    case "F": finalJson = neo4jService.printJson(querySet.getDetailOfGroom(valueMap)); break;
                 }
            }
        }
        else {
            //birth_death_linkage
            //switch(gender) { birth_groom, birth_bride}
            if(QuerySetIml.marriageRecordIsEmpty(groomCypher, brideCypher)){
                finalJson = neo4jService.printJson(querySet.getBirthDeathQuery(valueMap));
            }
            else {
                //die married birth
                switch (valueMap.get("gender")) {
                    case "M": finalJson = neo4jService.printJson(querySet.getDetailsAboutBrideAndBirth(valueMap));
                        System.out.println(finalJson);
                        break;
                    case "F": finalJson = neo4jService.printJson(querySet.getDetailsAboutGroomAndBirth(valueMap)); break;
                }
            }
        }
        return finalJson;
    }

    @Override
    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception {
        if(!params.get("dateOfMarriage").equals("null")) {
            //input marriageDate
            if (params.get("dateOfDeath").equals("null")) {
                //judge deathDate
                ///no deathDate
                switch (params.get("gender")) {
                    case "male":
                        finalJson = neo4jService.printJson(querySet.getBirthGroomQuery(map));
                        break;
                    case "female":
                        finalJson = neo4jService.printJson(querySet.getBirthBrideQuery(map));
                        break;
                    case "":
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthGroomQuery(map)), neo4jService.printJson(querySet.getBirthBrideQuery(map)));break;
                }
            } else {
                //
                finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathGroom(map)), neo4jService.printJson(querySet.getDeathBride(map)));
                System.out.println(finalJson);
                switch (params.get("gender")) {
                    case "male":
                        finalJson = neo4jService.printJson(querySet.getDeathGroom(map));
                        System.out.println(finalJson);
                        break;
                    case "female":
                        finalJson = neo4jService.printJson(querySet.getDeathBride(map));
                        break;
                    case "":
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathGroom(map)), neo4jService.printJson(querySet.getDeathBride(map)));break;
                }
            }
        }
        else {
            if (params.get("dateOfDeath").equals("null")) {
                //not married
                if (neo4jService.printJson(querySet.getBirthDeathQuery(map)).length() > 5) {
                    finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthDeathQuery(map)), neo4jService.printJson(querySet.addPeopleNotDie(map)));
                } else {
                    finalJson = neo4jService.printJson(querySet.addPeopleNotDie(map));
                }
            } else {
                finalJson = neo4jService.printJson(querySet.getBirthDeathQuery(map));
            }
        }
        return finalJson;
    }

    @Override
    public FamilyTree setTree(Map<String, String> params) throws Exception {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("gender", params.get("gender"));
        familyTree.getMember(valueMap);
        familyTree.getCategory();
        familyTree.getPointer(valueMap);
        return familyTree;
    }

    @Override
    public PersonalDetails setDetails(Map<String, String> params) throws Exception {
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("gender", params.get("gender"));
        if(!params.get("Death").equals("empty")) {
            valueMap.put("death", params.get("Death"));
        }
        else {valueMap.put("death", "");}

        personalDetails.getBirth(valueMap);
        personalDetails.getDeath(valueMap);
        personalDetails.getMarriage(valueMap);

        return personalDetails;
    }

    @Override
    public String toString() {
        return null;
    }
}
