package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.Neo4jServiceImpl;
import uk.ac.standrews.cs.service.QuerySet;
import uk.ac.standrews.cs.service.QuerySetIml;
import uk.ac.standrews.cs.service.Neo4jService;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Dongyao Liu
 * @Description Main data Controller
 **/

@Data
@RestController
@RequestMapping("/death")
public class DataController {
    @Autowired
    QuerySet querySet;
    @Autowired
    private Neo4jService neo4jService;
    Neo4jService neo;
    String cypher;
    String cypher2;
    StringBuilder finalJson = new StringBuilder();
    StringBuilder s1 = new StringBuilder();
    StringBuilder s2 = new StringBuilder();

    @ResponseBody
    @GetMapping(path="/queryByName")
    public StringBuilder BirthDeathByName(@RequestParam Map<String, String> params) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("foreName", params.get("foreName"));
        map.put("surName", params.get("surName"));
        map.put("sex", QuerySetIml.setSex(params.get("gender")));
        map.put("birth_Day", QuerySetIml.splitBirth(params.get("dateOfBirth"))[0]);
        map.put("birth_Month", QuerySetIml.splitBirth(params.get("dateOfBirth"))[1]);
        map.put("birth_Year", QuerySetIml.splitBirth(params.get("dateOfBirth"))[2]);
        map.put("death_Day", QuerySetIml.splitDeath(params.get("dateOfDeath"))[0]);
        map.put("death_Month", QuerySetIml.splitDeath(params.get("dateOfDeath"))[1]);
        map.put("death_Year", QuerySetIml.splitDeath(params.get("dateOfDeath"))[2]);
        map.put("marriage_Day", QuerySetIml.splitMarriage(params.get("dateOfMarriage"))[0]);
        map.put("marriage_Month", QuerySetIml.splitMarriage(params.get("dateOfMarriage"))[1]);
        map.put("marriage_Year", QuerySetIml.splitMarriage(params.get("dateOfMarriage"))[2]);


        if(!params.get("dateOfMarriage").equals("null")) {
            switch (params.get("gender")) {
                case "male":
                    cypher = querySet.getBirthGroomQuery(map);
                    finalJson = neo4jService.printJson(cypher);
                    break;
                case "female":
                    cypher = querySet.getBirthBrideQuery(map);
                    finalJson = neo4jService.printJson(cypher);
                    break;
                default:
                    cypher = querySet.getBirthGroomQuery(map);
                    cypher2 = querySet.getBirthBrideQuery(map);
                    s1 = neo4jService.printJson(cypher);
                    s2 = neo4jService.printJson(cypher2);
                    finalJson = Neo4jServiceImpl.linkJson(s1, s2);
                    System.out.println(finalJson);
            }
        }
        else {
            cypher = querySet.getBirthDeathQuery(map);
            finalJson = neo4jService.printJson(cypher);
        }

        return finalJson;
    }

}
