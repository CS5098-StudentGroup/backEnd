package uk.ac.standrews.cs.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.pojo.Birth;
import uk.ac.standrews.cs.dao.BirthRepository;
import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class BirthServiceImpl implements BirthService{
    @Autowired
    private BirthRepository birthRepository;

    @Override
    public List<Birth> findName(String SURNAME, String FORENAME) {
        return birthRepository.findName(SURNAME, FORENAME);
    }


}
