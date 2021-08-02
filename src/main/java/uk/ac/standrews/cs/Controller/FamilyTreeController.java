package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.Pojo.Category;
import uk.ac.standrews.cs.Pojo.FamilyTree;
import uk.ac.standrews.cs.Pojo.Person;
import uk.ac.standrews.cs.service.GetInfo;
import uk.ac.standrews.cs.service.Neo4jService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: backEnd
 * @description: receive data and return family tree
 * @author: Dongyao Liu
 * @create: 2021-08-01 19:41
 **/

@Data
@RestController
@RequestMapping("/familyTree")
public class FamilyTreeController {
    @Autowired
    FamilyTree familyTree;
    @Autowired
    Neo4jService neo4jService;
    @Autowired
    GetInfo getInfo;

    @GetMapping(path="/getFamilyTree")
    public @ResponseBody FamilyTree getDetails(@RequestParam Map<String, String> params) {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("gender", params.get("gender"));
        familyTree.getMember(valueMap);
        familyTree.getCategory();
        familyTree.getPointer(valueMap);
        return familyTree;
        /*return familyTree.getMember(valueMap);*/
    }

}
