package uk.ac.standrews.cs.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.dao.DeathRepository;
import uk.ac.standrews.cs.pojo.Death;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeathServiceImpl implements DeathService {

    @Autowired
    private DeathRepository deathRepository;

    @Override
    public List<Death> findByName(String surName, String foreName, String gender, String dateOfBirth, String deathDay, String deathMonth, String deathYear,  String dateOfMarriage) {
        return deathRepository.findByName(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
    }
}
