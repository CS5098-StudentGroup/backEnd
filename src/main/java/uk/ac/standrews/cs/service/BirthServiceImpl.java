package uk.ac.standrews.cs.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.standrews.cs.pojo.Birth;
import uk.ac.standrews.cs.dao.BirthRepository;
import java.util.List;

@Component
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BirthServiceImpl implements BirthService{

    @Autowired
    BirthRepository birthRepository;


    @Override
    public List<Birth> findByName (String SURNAME,String FORENAME)
    {
        return birthRepository.findByName(SURNAME, FORENAME);
    }
}
