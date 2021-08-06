package uk.ac.standrews.cs.Pojo.Parents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.service.GetParents.GetMotherQuery;

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
public class Mother {
    @Autowired
    GetMotherQuery getMotherQuery;
    BirthRecords birthRecords;
    DeathRecords deathRecords;
    List<MarriageRecords> marriageRecordsList = new ArrayList<>();

    public void getMotherBirth(Map<String, String> valueMap) throws Exception {
        birthRecords = getMotherQuery.getBirthRecords(valueMap);
    }
    public void getMotherDeath(Map<String, String> valueMap) throws Exception {
        deathRecords = getMotherQuery.getDeathRecords(valueMap);
    }
    public void getMotherMarriage(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList = getMotherQuery.getMarriageRecords(valueMap);
    }
}
