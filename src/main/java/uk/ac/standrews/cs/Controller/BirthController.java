package uk.ac.standrews.cs.Controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uk.ac.standrews.cs.dao.BirthRepository;
import uk.ac.standrews.cs.pojo.Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.ac.standrews.cs.service.BirthService;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/birth")
public class BirthController {

    @Autowired
    BirthService birthService;
    BirthRepository birthRepository;

    //List all the records of birth distinguished by foreName
//    @GetMapping("/foreName/{FORENAME}")
//    List<Birth> byForeName(@PathVariable String FORENAME) {
//        return birthService.findByForeName(FORENAME);
//    }
//
//    //List all the records of birth distinguished by surName
//    @GetMapping("/surName/{SURNAME}")
//    List<Birth> bySurName(@PathVariable String SURNAME) {
//        return birthRepository.findBySurName(SURNAME);
//    }

    //List all the records of birth distinguished by fullName
    @GetMapping("/queryByName/{SURNAME}/{FORENAME}")
    public List<Birth> byName(@PathVariable String SURNAME, @PathVariable String FORENAME) {
        return birthService.findByName(SURNAME,FORENAME);
    }
}
