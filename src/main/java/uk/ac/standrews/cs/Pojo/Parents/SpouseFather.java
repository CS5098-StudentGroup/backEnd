package uk.ac.standrews.cs.Pojo.Parents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.BirthRecords;
import uk.ac.standrews.cs.Pojo.details.DeathRecords;
import uk.ac.standrews.cs.Pojo.details.MarriageRecords;
import uk.ac.standrews.cs.service.GetParents.GetFatherQuery;
import uk.ac.standrews.cs.service.GetParents.GetSpouseFatherQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpouseFather {

    @Autowired
    GetSpouseFatherQuery getFatherQuery;
    BirthRecords birthRecords;
    DeathRecords deathRecords;
    List<MarriageRecords> marriageRecordsList = new ArrayList<>();

    public void getSpouseFatherBirth(Map<String, String> valueMap) throws Exception {
        birthRecords =  getFatherQuery.getBirthRecords(valueMap);
    }
    public void getSpouseFatherDeath(Map<String, String> valueMap) throws Exception {
        deathRecords =  getFatherQuery.getDeathRecords(valueMap);
    }
    public void getSpouseFatherMarriage(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList =  getFatherQuery.getMarriageRecords(valueMap);
    }
}
