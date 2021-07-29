package uk.ac.standrews.cs.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/death")
public class DataController {
    @Autowired
    QuerySet querySet;
    @Autowired
    private Neo4jService neo4jService;
    String dateOfMarriage;
    String birthDay;
    String birthMonth;
    String birthYear;
    String deathDay;
    String deathMonth;
    String deathYear;
    String cypher;
    String marriageDay;
    String marriageMonth;
    String marriageYear;

    @ResponseBody
    @GetMapping(path="/queryByName")
    public StringBuilder BirthDeathByName(@RequestParam Map<String, String> params) throws Exception {
        splitDeath(params.get("dateOfDeath"));
        splitBirth(params.get("dateOfBirth"));
        splitMarriage(params.get("dateOfMarriage"));

        Map<String, String> map = new HashMap<>();
        map.put("foreName", params.get("foreName"));
        map.put("surName", params.get("surName"));
        map.put("sex", QuerySetIml.setSex(params.get("gender")));
        map.put("birth_Day", birthDay);
        map.put("birth_Month", birthMonth);
        map.put("birth_Year", birthYear);
        map.put("death_Day", deathDay);
        map.put("death_Month", deathMonth);
        map.put("death_Year", deathYear);
        map.put("marriage_Day", marriageDay);
        map.put("marriage_Month", marriageMonth);
        map.put("marriage_Year", marriageYear);


        if(!params.get("dateOfMarriage").equals("null")) {
            switch (params.get("gender")) {
                case "male":
                    cypher = querySet.getBirthGroomQuery(map);
                    break;
                case "female":
                    cypher = querySet.getBirthBrideQuery(map);
                    break;
                default:
                    cypher = querySet.getMarriageQuery(map);
                    break;
            }
        }
        else {
            cypher = querySet.getBirthDeathQuery(map);
        }


        return neo4jService.printJson(cypher);
    }


    public void splitBirth(String dateOfBirth) {
        if (!dateOfBirth.equals("null")) {
            birthDay = dateOfBirth.split("-")[0];
            birthMonth = dateOfBirth.split("-")[1];
            birthYear = dateOfBirth.split("-")[2];
        } else {
            birthDay = null;
            birthMonth = null;
            birthYear = null;
        }
    }


    public void splitDeath(String dateOfDeath) {
        if (!dateOfDeath.equals("null")) {
            deathDay = dateOfDeath.split("-")[0];
            deathMonth = dateOfDeath.split("-")[1];
            deathYear = dateOfDeath.split("-")[2];
        } else {
            deathDay = null;
            deathMonth = null;
            deathYear = null;
        }
    }

    public void splitMarriage(String dateOfMarriage) {
        if (!dateOfMarriage.equals("null")) {
            marriageDay = dateOfMarriage.split("-")[0];
            marriageMonth = dateOfMarriage.split("-")[1];
            marriageYear = dateOfMarriage.split("-")[2];
        } else {
            marriageYear = null;
            marriageMonth = null;
            marriageDay = null;
        }
    }
}
