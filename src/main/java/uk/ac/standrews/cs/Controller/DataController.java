package uk.ac.standrews.cs.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.neoStorr.util.NeoDbCypherBridge;
import uk.ac.standrews.cs.pojo.Birth;
import uk.ac.standrews.cs.service.BirthService;
import uk.ac.standrews.cs.service.DeathService;
import uk.ac.standrews.cs.service.Neo4jService;
import java.util.List;
import java.util.Map;


/**
 * @Author Dongyao Liu
 * @Description 主控器
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/death")
public class DataController {
    @Autowired
    DeathService deathService;
    @Autowired
    BirthService birthService;
    @Autowired
    private Neo4jService neo4jService;
    private Driver driver;
    String foreName;
    String surName;
    String gender;
    String dateOfBirth;
    String dateOfDeath;
    String dateOfMarriage;
    String[] birth;
    String birthDay;
    String birthMonth;
    String birthYear;
    String[] death;
    String deathDay;
    String deathMonth;
    String deathYear;
    String query;
    String deaths;
    List<Birth> births;
    Result result;
    NeoDbCypherBridge bridge = new NeoDbCypherBridge();

    @ResponseBody
    @GetMapping(path="/queryByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject byName(@RequestParam Map<String, String> params) throws Exception {
        this.foreName = params.get("foreName");
        this.surName = params.get("surName");
        dateOfDeath = params.get("dateOfDeath");
        dateOfBirth = params.get("dateOfBirth");
        splitDeath(dateOfDeath);
        splitBirth(dateOfBirth);

        switch (params.get("gender")) {
            case "male":
                this.gender = "M";
                break;
            case "female":
                this.gender = "F";
                break;
            default:
                gender = null;
                break;
        }
        /*births = birthService.findData(surName, foreName, gender, birthDay, birthMonth, birthYear);*/
        deaths = deathService.findData(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear);
        System.out.println("controller:" + deaths);
        return neo4jService.printJson(deaths);
    }


    private void splitBirth(String dateOfBirth) {
        if (!dateOfBirth.equals("null")) {
            birth = dateOfBirth.split("-");
            birthDay = birth[0];
            birthMonth = birth[1];
            birthYear = birth[2];
        } else {
            birthDay = null;
            birthMonth = null;
            birthYear = null;
        }
    }


    private void splitDeath(String dateOfDeath) {
        if (!dateOfDeath.equals("null")) {
            death = dateOfDeath.split("-");
            deathDay = death[0];
            deathMonth = death[1];
            deathYear = death[2];
        } else {
            deathDay = null;
            deathMonth = null;
            deathYear = null;
        }
    }
}
