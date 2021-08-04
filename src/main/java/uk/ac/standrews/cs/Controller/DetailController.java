package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: backEnd
 * @description: processing data from front-end and return details
 * @author: Dongyao Liu
 * @create: 2021-07-29 22:02
 **/

@Data
@RestController
@RequestMapping("/details")
public class DetailController {
    @Autowired
    QuerySet querySet;
    @Autowired
    Neo4jService neo4jService;
    @Autowired
    Judge judge;
    StringBuilder finalJson = new StringBuilder();
    @ResponseBody
    @GetMapping(path="/getDetails")
    public StringBuilder getDetails(@RequestParam Map<String, String> params) throws Exception {
        System.out.println(params.toString());
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("gender", params.get("gender"));
        valueMap.put("death", params.get("Death"));
        return judge.getFinalJson(valueMap);
    }
}