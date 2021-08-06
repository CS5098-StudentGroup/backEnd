package uk.ac.standrews.cs.Controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.service.IDSearch;

import java.util.Map;

/**
 * @program: backEnd
 * @description: receive various IDs
 * @author: Dongyao Liu
 * @create: 2021-08-02 22:49
 **/

@Data
@RestController
@RequestMapping("/id")
public class IDController {
    @Autowired
    IDSearch idSearch;

    @ResponseBody
    @GetMapping("/getIDs")
    public PersonalDetails getBirthID(@RequestParam Map<String, String> params) throws Exception {
        return idSearch.setDetails(params);
    }
}
