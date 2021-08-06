package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.service.CommonTool.Judge;

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
    Judge judge;

    @GetMapping(path="/getDetails")
    public @ResponseBody PersonalDetails getDetails(@RequestParam Map<String, String> params) throws Exception {
        return judge.setDetails(params);
    }
}