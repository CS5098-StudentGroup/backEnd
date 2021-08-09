package uk.ac.standrews.cs.Pojo.Spouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.service.GetSpouse.GetSpouseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-08-06 20:07
 **/

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Spouse {
    @Autowired
    GetSpouseQuery getSpouseQuery;
    BirthRecords birthRecords;
    DeathRecords deathRecords;
    List<MarriageRecords> marriageRecordsList = new ArrayList<>();

    public void getSpouseBirth(Map<String, String> valueMap) throws Exception {
        birthRecords = getSpouseQuery.getBirthRecords(valueMap);
    }
    public void getSpouseDeath(Map<String, String> valueMap) throws Exception {
        deathRecords = getSpouseQuery.getDeathRecords(valueMap);
    }
    public void getSpouseMarriage(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList = getSpouseQuery.getMarriageRecords(valueMap);
    }
}
