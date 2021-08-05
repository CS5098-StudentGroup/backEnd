package uk.ac.standrews.cs.service;

import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import java.util.HashMap;
import java.util.Map;
@Data
@Service
public class IDSearchImpl implements IDSearch {

    @Autowired
    QuerySet querySet;
    @Autowired
    Neo4jService neo4jService;
    @Autowired
    IDSearch idSearch;
    @Autowired
    FamilyTree familyTree = new FamilyTree();
    StringBuilder groomCypher;
    StringBuilder brideCypher;
    StringBuilder finalJson = new StringBuilder();
    public static final String EMPTY = "empty";

    @Override
    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception {
        if(!params.get("standardisedId").equals("null")) {
            //input marriageDate
            if (params.get("storrId").equals("null")) {

                if (params.get("originalId").equals("")) {
//                    case "male":
//                        finalJson = neo4jService.printJson(querySet.getBirthGroomQuery(map));
//                        break;
//                    case "female":
//                        finalJson = neo4jService.printJson(querySet.getBirthBrideQuery(map));
//                        break;
//                    case "":
//                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthGroomQuery(map)), neo4jService.printJson(querySet.getBirthBrideQuery(map)));break;
                }
            } else {
                //
//                finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathGroom(map)), neo4jService.printJson(querySet.getDeathBride(map)));
//                System.out.println(finalJson);
//                switch (params.get("gender")) {
//                    case "male":
//                        finalJson = neo4jService.printJson(querySet.getDeathGroom(map));
//                        System.out.println(finalJson);
//                        break;
//                    case "female":
//                        finalJson = neo4jService.printJson(querySet.getDeathBride(map));
//                        break;
//                    case "":
//                        finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getDeathGroom(map)), neo4jService.printJson(querySet.getDeathBride(map)));
//                        System.out.println(finalJson);break;
//                }
            }
        }
        else {
//            if (params.get("dateOfDeath").equals("null")) {
//                //not married
//                if (neo4jService.printJson(querySet.getBirthDeathQuery(map)).length() > 5) {
//                    finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthDeathQuery(map)), neo4jService.printJson(querySet.addPeopleNotDie(map)));
//                } else {
//                    finalJson = neo4jService.printJson(querySet.addPeopleNotDie(map));
//                }
//            } else {
//                finalJson = neo4jService.printJson(querySet.getBirthDeathQuery(map));
//            }
        }
        return finalJson;
    }

    @Override
    public void getParams(Map<String, String> map) {
        Map<String,String> setMap = new HashMap<>();
        setMap.put("standardised_ID", map.get("standardised_id"));
        setMap.put("storr_ID", map.get("storr_id"));
        setMap.put("original_Id", map.get("original_id"));
    }

}
