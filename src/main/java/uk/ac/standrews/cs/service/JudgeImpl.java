package uk.ac.standrews.cs.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-30 23:00
 **/
@Data
@Service
public class JudgeImpl implements Judge{

    String birthDeathCypher;
    String marriageCypher;
    @Autowired
    QuerySet querySet;
    @Autowired
    Neo4jService neo4jService;
    Judge judge;
    String isDeath;
    StringBuilder groomCypher;
    StringBuilder brideCypher;
    StringBuilder finalJson = new StringBuilder();
    @Override
    public StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception {
        groomCypher = neo4jService.printJson(querySet.getBirthGroomQuery(valueMap));
        brideCypher = neo4jService.printJson(querySet.getBirthBrideQuery(valueMap));

        if(valueMap.get("death").equals("")) {
            //not die
            //birth + marriage(switch:gender)
            if(QuerySetIml.marriageRecordIsEmpty(groomCypher, brideCypher)){
                finalJson = neo4jService.printJson(querySet.addPeopleNotDie(valueMap));
            }
            else {
                //die married birth
                switch (valueMap.get("gender")) {
                    case "M": finalJson = neo4jService.printJson(querySet.getBirthGroomQuery(valueMap));
                    break;
                    case "F": finalJson = neo4jService.printJson(querySet.getBirthBrideQuery(valueMap)); break;
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
                switch (valueMap.get("gender")) {
                    case "M": finalJson = neo4jService.printJson(querySet.getDetailsAboutGroomAndBirth(valueMap));
                        System.out.println(finalJson);
                        break;
                    case "F": finalJson = neo4jService.printJson(querySet.getDetailsAboutBrideAndBirth(valueMap)); break;
                }
            }
        }
        return finalJson;
    }

    @Override
    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception {
        if(!params.get("dateOfMarriage").equals("null")) {
            switch (params.get("gender")) {
                case "M":
                    finalJson = neo4jService.printJson(querySet.getBirthGroomQuery(map));
                    break;
                case "F":
                    finalJson = neo4jService.printJson(querySet.getBirthBrideQuery(map));
                    break;
                default:
                    finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthGroomQuery(map)), neo4jService.printJson(querySet.getBirthBrideQuery(map)));
            }
        }
        else {
            if(params.get("dateOfDeath").equals("null")) {

                if(neo4jService.printJson(querySet.getBirthDeathQuery(map)).length() > 5) {
                    finalJson = Neo4jServiceImpl.linkJson(neo4jService.printJson(querySet.getBirthDeathQuery(map)), neo4jService.printJson(querySet.addPeopleNotDie(map)));
                }
                else {
                    finalJson = neo4jService.printJson(querySet.addPeopleNotDie(map));
                }
            }
            else {
                finalJson = neo4jService.printJson(querySet.getBirthDeathQuery(map));
            }
        }
        return finalJson;
    }
}
