package uk.ac.standrews.cs.service;

import lombok.Data;
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
    QuerySet querySet;
    Neo4jService neo4jService;
    Judge judge;
    String isDeath;
    String groomCypher;
    String brideCypher;
    StringBuilder finalJson = new StringBuilder();
    @Override
    public StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception {
        groomCypher = querySet.getBirthGroomQuery(valueMap);
        brideCypher = querySet.getBirthBrideQuery(valueMap);

        if(valueMap.get("death").equals("")) {
            //not die
            //birth + marriage(switch:gender)
            if(QuerySetIml.marriageRecordIsEmpty(groomCypher, brideCypher)){
                finalJson = neo4jService.printJson(querySet.addPeopleNotDie(valueMap));
            }
            else {
                switch (valueMap.get("gender")) {
                    case "M": finalJson = neo4jService.printJson(querySet.getBirthGroomQuery(valueMap)); break;
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
                    case "M": finalJson = neo4jService.printJson(querySet.getDetailsAboutGroomAndBirth(valueMap)); break;
                    case "F": finalJson = neo4jService.printJson(querySet.getDetailsAboutBrideAndBirth(valueMap)); break;
                }
            }
        }
        return finalJson;
    }
}
