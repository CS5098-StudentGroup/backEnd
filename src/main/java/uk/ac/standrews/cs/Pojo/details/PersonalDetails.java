package uk.ac.standrews.cs.Pojo.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.service.DetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-08-05 12:00
 **/

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalDetails {

    @Autowired
    DetailsService detailsService;

    BirthRecords birthRecords;
    DeathRecords deathRecords;
    List<MarriageRecords> marriageRecordsList = new ArrayList<>();


    public void getBirth(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsService.getBirthRecords(valueMap);
    }

    public void getDeath(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsService.getDeathRecords(valueMap);
    }

    public void getMarriage(Map<String, String> valueMap) throws Exception {
        /*marriageRecordsList = detailsService.getMarriageRecords(valueMap);*/
        marriageRecordsList.addAll(detailsService.getMarriageRecords(valueMap));
    }
}
