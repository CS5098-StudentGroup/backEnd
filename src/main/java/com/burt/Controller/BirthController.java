package com.burt.Controller;

import com.burt.dao.BirthRepository;
import com.burt.Entity.Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/birth")
public class BirthController {
    @Autowired
     BirthRepository birthRepository;

    public BirthController(BirthRepository birthRepository) {
        this.birthRepository = birthRepository;
    }

    @GetMapping("/{FORENAME}")
    List<Birth> byName(@PathVariable String FORENAME) {
        return birthRepository.findByForeName(FORENAME);
    }
}
