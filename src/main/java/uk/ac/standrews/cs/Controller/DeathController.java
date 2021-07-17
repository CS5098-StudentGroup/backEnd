package uk.ac.standrews.cs.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.pojo.Death;
import uk.ac.standrews.cs.service.DeathService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/death")
public class DeathController {

    @Autowired
    private DeathService deathService;


    @ResponseBody
    @GetMapping("/queryByName/{SURNAME}/{FORENAME}")
    public List<Death> byName(@PathVariable String SURNAME, @PathVariable String FORENAME){
        return deathService.findByName(SURNAME, FORENAME);
    }
}
