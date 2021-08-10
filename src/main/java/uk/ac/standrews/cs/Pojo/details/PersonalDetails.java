package uk.ac.standrews.cs.Pojo.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.service.Details.DetailsService;
import uk.ac.standrews.cs.service.GetID.GetBirthById;
import uk.ac.standrews.cs.service.GetID.GetDeathById;
import uk.ac.standrews.cs.service.GetID.GetMarriageById;
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
    @Autowired
    GetBirthById detailsServiceByBirthId;
    @Autowired
    GetDeathById detailsServiceByDeathId;
    @Autowired
    GetMarriageById detailsServiceByMarriageId;


    BirthRecords birthRecords;
    DeathRecords deathRecords;
    List<MarriageRecords> marriageRecordsList = new ArrayList<>();
    MarriageRecords marriageRecords;


    public void getBirth(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsService.getBirthRecords(valueMap);
    }

    public void getDeath(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsService.getDeathRecords(valueMap);
    }

    public void getMarriage(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList.addAll(detailsService.getMarriageRecords(valueMap));
    }


    //only death
    public void getOnlyBirth(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsService.getOnlyBirthRecords(valueMap);
    }

    public void getOnlyDeath(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsService.getOnlyDeathRecords(valueMap);
    }

    public void getOnlyMarriage(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList.addAll(detailsService.getOnlyMarriageRecords(valueMap));
    }




    public void getBirthByBirthId(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsServiceByBirthId.getBirthById(valueMap);
    }

    public void getDeathByBirthId(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsServiceByBirthId.getDeathById(valueMap);
    }

    public void getMarriageByBirthId(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList.addAll(detailsServiceByBirthId.getMarriageById(valueMap));
    }






    public void getBirthByDeathId(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsServiceByDeathId.getBirthByDeathId(valueMap);
    }

    public void getDeathByDeathId(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsServiceByDeathId.getDeathByDeathId(valueMap);
    }

    public void getMarriageByDeathId(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList.addAll(detailsServiceByDeathId.getMarriageByDeathId(valueMap));
    }




    public void getBirthByMarriageGroomId(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsServiceByMarriageId.getBirthByMarriageGroomId(valueMap);
    }

    public void getDeathByMarriageGroomId(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsServiceByMarriageId.getDeathByMarriageGroomId(valueMap);
    }

    public void getMarriageByMarriageGroomId(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList.addAll(detailsServiceByMarriageId.getMarriageByMarriageGroomId(valueMap));
    }

    public void getBirthByMarriageBrideId(Map<String, String> valueMap) throws Exception {
        birthRecords = detailsServiceByMarriageId.getBirthByMarriageBrideId(valueMap);
    }

    public void getDeathByMarriageBrideId(Map<String, String> valueMap) throws Exception {
        deathRecords = detailsServiceByMarriageId.getDeathByMarriageBrideId(valueMap);
    }

    public void getMarriageByMarriageBrideId(Map<String, String> valueMap) throws Exception {
        marriageRecordsList.clear();
        marriageRecordsList.addAll(detailsServiceByMarriageId.getMarriageByMarriageBrideId(valueMap));
    }
}
