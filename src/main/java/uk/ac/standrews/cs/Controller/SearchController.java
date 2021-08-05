package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Dongyao Liu
 * @Description Main data Controller
 **/

@Data
@RestController
@RequestMapping("/death")
public class SearchController {
    @Autowired
    Judge judge;

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

        return judge.setJson(map,params);
    }

}
