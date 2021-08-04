package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.Pojo.FamilyTree;
import uk.ac.standrews.cs.service.Judge;
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
    Judge judge;


    @GetMapping(path="/getFamilyTree")
    public @ResponseBody FamilyTree getDetails(@RequestParam Map<String, String> params) {
        return judge.setTree(params);
    }

}
