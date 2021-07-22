package uk.ac.standrews.cs.service;

import uk.ac.standrews.cs.pojo.Birth;
import java.util.List;


public interface BirthService {
    List<Birth> findData (String surName, String foreName, String gender, String birthDay, String birthMonth, String birthYear);
}
