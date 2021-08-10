package uk.ac.standrews.cs.service.CommonTool;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import uk.ac.standrews.cs.service.Search.QuerySet;
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

    StringBuilder groomCypher;
    StringBuilder brideCypher;
    StringBuilder finalJson = new StringBuilder();
    public static final String EMPTY = "empty";
    Map<String, String> valueMap = new HashMap<>();


    @Override
    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception {
        if(!params.get("dateOfMarriage").equals("null")) {
            //input marriageDate
            if (params.get("dateOfDeath").equals("null")) {
                //judge deathDate
                ///no deathDate
                //has marriageDate and no deathDate
                switch (params.get("gender")) {
                    case "male":
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthGroomQuery(map)),neo4jService.printJson(querySet.getDeathQuery(map)));
                        break;
                    case "female":
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthBrideQuery(map)),neo4jService.printJson(querySet.getDeathQuery(map)));
                        break;
                    case "":
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthGroomQuery(map)), neo4jService.printJson(querySet.getBirthBrideQuery(map)));break;
                }
            } else {
                //has marriageDate and deathDate
                switch (params.get("gender")) {
                    case "male":
                        finalJson =  Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathGroom(map)),neo4jService.printJson(querySet.getBirthBrideQuery(map)));
                        break;
                    case "female":
                        finalJson =  Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathBride(map)),neo4jService.printJson(querySet.getBirthBrideQuery(map)));
                        break;
                    case "":
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathGroom(map)), neo4jService.printJson(querySet.getDeathBride(map)));break;
                }
            }
        }
        else {
            if (params.get("dateOfDeath").equals("null")) {
                //not marriedDate input
                if (neo4jService.printJson(querySet.getBirthDeathQuery(map)).length() > 3 && neo4jService.printJson(querySet.addPeopleNotDie(map)).length() > 3 ) {
                    finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthDeathQuery(map)), neo4jService.printJson(querySet.addPeopleNotDie(map)));
                } else {
                    if(querySet.getDeathQuery(map).length() > 3 && querySet.addPeopleNotDie(map).length() > 3) {
                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.addPeopleNotDie(map)), neo4jService.printJson(querySet.getDeathQuery(map)));
                    }
                    else { finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.addPeopleNotDie(map)), neo4jService.printJson(querySet.getDeathQuery(map))); }
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
    public FamilyTree setDeathTree(Map<String, String> params) throws Exception {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("death_standardised_ID", params.get("death_standardised_id"));
        valueMap.put("gender", params.get("gender"));
        familyTree.getDeathMember(valueMap);
        familyTree.getCategory();
        familyTree.getDeathPointer(valueMap);
        return familyTree;
    }

    @Override
    public PersonalDetails setDeathDetails(Map<String, String> params) throws Exception {
        valueMap.put("death_standardised_ID", params.get("death_standardised_id"));
        valueMap.put("gender", params.get("gender"));
        personalDetails.getOnlyBirth(valueMap);
        personalDetails.getOnlyDeath(valueMap);
        personalDetails.getOnlyMarriage(valueMap);

        return personalDetails;
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
