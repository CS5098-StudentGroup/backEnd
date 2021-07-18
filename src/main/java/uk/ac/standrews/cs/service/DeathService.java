package uk.ac.standrews.cs.service;

import uk.ac.standrews.cs.pojo.Death;
import java.util.List;


public interface DeathService {
    List<Death> findByName (String surName, String foreName, String gender, String dateOfBirth, String dateOfDeath, String dateOfMarriage);
}
