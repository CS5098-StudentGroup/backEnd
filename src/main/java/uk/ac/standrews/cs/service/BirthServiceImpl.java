package uk.ac.standrews.cs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.pojo.Birth;
import uk.ac.standrews.cs.dao.BirthRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BirthServiceImpl implements BirthService{

    private BirthRepository birthRepository;
    private Birth birth;

    public List<Birth> findByName (String surName, String foreName) throws JsonProcessingException {
        return birthRepository.findByName(surName,foreName);
    }
}