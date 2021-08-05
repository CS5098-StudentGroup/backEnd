package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.IDSearch;
import uk.ac.standrews.cs.service.Judge;
import uk.ac.standrews.cs.service.QuerySetIml;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: backEnd
 * @description: receive various IDs
 * @author: Dongyao Liu
 * @create: 2021-08-02 22:49
 **/

@Data
@RestController
@RequestMapping("/death")
public class IDController {
    @Autowired
    IDSearch idSearch;

    @ResponseBody
    @GetMapping("/querybyId")
    public StringBuilder getBirthID(@RequestParam Map<String, String> params) throws Exception {
        //idSearch.getParams(params);
        Map<String, String> map = new HashMap<>();
        map.put("STORR_ID", params.get("storrId"));
        map.put("ORIGINAL_ID", params.get("originalId"));
        map.put("STANDARDISED_ID", params.get("standardisedId"));
        return idSearch.setJson(map,params);
    }
}
