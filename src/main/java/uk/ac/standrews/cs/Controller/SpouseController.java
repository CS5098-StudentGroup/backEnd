package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.standrews.cs.Pojo.Spouse.Spouse;

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
public class SpouseController {
    @Autowired
    Spouse spouse = new Spouse();

    Map<String, String> valueMap = new HashMap<>();


    @RequestMapping("/getSpouse")
    public @ResponseBody
    Spouse getSpouse(@RequestParam Map<String, String> params) throws Exception {
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("marriage_standardised_ID", params.get("marriage_standardised_id"));
        spouse.getSpouseBirth(valueMap);
        spouse.getSpouseDeath(valueMap);
        spouse.getSpouseMarriage(valueMap);
        return spouse;

    }
}
