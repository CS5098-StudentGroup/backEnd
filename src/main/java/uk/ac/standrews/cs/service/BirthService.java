package uk.ac.standrews.cs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.pojo.Birth;

import java.util.List;


public interface BirthService {
    public List<Birth> findByName (String surName, String foreName) throws JsonProcessingException;
}
