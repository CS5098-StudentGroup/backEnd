package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.standrews.cs.Pojo.Parents.Father;
import uk.ac.standrews.cs.Pojo.Parents.Mother;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-08-06 12:27
 **/

@Data
@RestController
@RequestMapping("/info")
public class ParentsController {
    @Autowired
    Mother mother = new Mother();
    @Autowired
    Father father = new Father();
    Map<String, String> valueMap = new HashMap<>();


    @RequestMapping("/getFather")
    public @ResponseBody Father getFather(@RequestParam Map<String, String> params) throws Exception{
        valueMap.put("standardised_ID", params.get("standardised_id"));
        father.getFatherBirth(valueMap);
        father.getFatherDeath(valueMap);
        father.getFatherMarriage(valueMap);
        return father;

    }

    @RequestMapping("/getMother")
    public @ResponseBody
    Mother getMother(@RequestParam Map<String, String> params) throws Exception{
        valueMap.put("standardised_ID", params.get("standardised_id"));
        mother.getMotherBirth(valueMap);
        mother.getMotherDeath(valueMap);
        mother.getMotherMarriage(valueMap);
        return mother;
    }
}
