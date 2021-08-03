package uk.ac.standrews.cs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.standrews.cs.service.Judge;
import java.util.Map;

/**
 * @program: backEnd
 * @description: receive various IDs
 * @author: Dongyao Liu
 * @create: 2021-08-02 22:49
 **/

@RequestMapping("/id")
public class IDController {
    @Autowired
    Judge judge;

    @GetMapping("/birthID")
    public @ResponseBody StringBuilder getBirthID(@RequestParam Map<String, String> params) {
        judge.getParams(params);

        return null;
    }
}
