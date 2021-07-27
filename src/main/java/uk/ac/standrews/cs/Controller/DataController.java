package uk.ac.standrews.cs.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.BirthDeathService;
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
    BirthDeathService birthDeathService;
    @Autowired
    private Neo4jService neo4jService;
    String gender;
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
    String birthDeath;


    @ResponseBody
    @GetMapping(path="/queryByName")
    public StringBuilder BirthDeathByName(@RequestParam Map<String, String> params) throws Exception {
        splitDeath(params.get("dateOfDeath"));
        splitBirth(params.get("dateOfBirth"));
        setSex(params.get("gender"));

        Map<String, String> map = new HashMap<>();
        map.put("foreName", params.get("foreName"));
        map.put("surName", params.get("surName"));
        map.put("sex", gender);
        map.put("birth_Day", birthDay);
        map.put("birth_Month", birthMonth);
        map.put("birth_Year", birthYear);
        map.put("death_Day", deathDay);
        map.put("death_Month", deathMonth);
        map.put("death_Year", deathYear);

        birthDeath = birthDeathService.getQuery(map);
        return neo4jService.printJson(birthDeath);
    }

    public void setSex(String sex){
        switch (sex) {
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
    }
    public void splitBirth(String dateOfBirth) {
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


    public void splitDeath(String dateOfDeath) {
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
