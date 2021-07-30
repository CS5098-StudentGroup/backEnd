package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.Neo4jService;
import uk.ac.standrews.cs.service.Neo4jServiceImpl;
import uk.ac.standrews.cs.service.QuerySet;
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
    String birthDeathCypher;
    String marriageCypher;
    QuerySet querySet;
    Neo4jService neo4jService;
    @ResponseBody
    @GetMapping(path="/getDetails")
    public StringBuilder getDetails(@RequestParam Map<String, String> params) throws Exception {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("standardised_ID",params.get("standardised_id"));
        valueMap.put("gender", params.get("gender"));

        birthDeathCypher = querySet.getBirthDeathQuery(valueMap);
        switch (params.get("gender")) {
            case "M":
                marriageCypher = querySet.getGroomQuery(valueMap); break;
            case "F":
                marriageCypher = querySet.getBrideQuery(valueMap); break;
        }

        return Neo4jServiceImpl.linkJson(neo4jService.printJson(birthDeathCypher), neo4jService.printJson(marriageCypher));
    }
}
