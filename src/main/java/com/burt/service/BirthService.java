package com.burt.service;

import com.burt.dao.BirthRepository;
import com.burt.impl.Birth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BirthService {
    @Autowired
    BirthRepository birthRepository;

    @GetMapping("/STORR_LXP/{FORENAME}")
    public List<Birth> findName(@PathVariable("FORENAME") String FORENAME) {
        return birthRepository.findByName(FORENAME);
    }
}
