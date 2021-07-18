package uk.ac.standrews.cs.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.pojo.Death;
import uk.ac.standrews.cs.service.DeathService;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/death")
public class DeathController {

    @Autowired
    DeathService deathService;
    String foreName;
    String surName;
    String gender;
    String dateOfBirth;
    String[] dateOfDeath;
    String dateOfMarriage;

    @ResponseBody
    @GetMapping("/queryByName")
    public List<Death> byName(@RequestParam Map<String, String> params){
        this.foreName = params.get("foreName");
        this.surName = params.get("surName");
        this.dateOfBirth = params.get("dateOfBirth");
        this.dateOfMarriage = params.get("dateOfMarriage");

        this.dateOfDeath = params.get("dateOfDeath").split("-");
        String deathDay = dateOfDeath[0];
        String deathMonth = dateOfDeath[1];
        String deathYear = dateOfDeath[2];

        switch (params.get("gender")){
            case "male" : this.gender = "M";break;
            case "female" : this.gender = "F";break;
            default: this.gender = null;
        }
        System.out.println(params);
        return deathService.findByName(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
    }
}
