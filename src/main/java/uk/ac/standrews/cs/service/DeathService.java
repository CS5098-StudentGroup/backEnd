package uk.ac.standrews.cs.service;


import uk.ac.standrews.cs.pojo.Death;

import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author Dongyao Liu
 * @Description Service interface
 **/
public interface DeathService {
    String getQuery (String surName, String foreName, String gender, String dateOfBirth, String deathDay, String deathMonth, String deathYear);
}
