package uk.ac.standrews.cs.service.GetID;

import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import uk.ac.standrews.cs.service.CommonTool.Neo4jService;
import uk.ac.standrews.cs.service.Search.QuerySet;

import java.util.HashMap;

@Data
@Service
public class IDSearchImpl implements IDSearch {

    @Autowired
    QuerySet querySet;
    @Autowired
    Neo4jService neo4jService;
    @Autowired
    IDSearch idSearch;
    @Autowired
    FamilyTree familyTree = new FamilyTree();
    @Autowired
    PersonalDetails personalDetails = new PersonalDetails();

    Map<String, String> detail = new HashMap<>();


    StringBuilder groomCypher;
    StringBuilder brideCypher;
    StringBuilder finalJson = new StringBuilder();
    public static final String EMPTY = "empty";
    Map<String, String> valueMap = new HashMap<>();

    @Override
    public PersonalDetails setDetailsByBirthId(Map<String, String> params) throws Exception {
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("storr_ID", params.get("storr_id"));
        valueMap.put("original_ID", params.get("original_id"));

        personalDetails.getBirthByBirthId(valueMap);
        personalDetails.getDeathByBirthId(valueMap);
        personalDetails.getMarriageByBirthId(valueMap);

        return personalDetails;
    }

    @Override
    public PersonalDetails setDetailsByDeathId(Map<String, String> params) throws Exception {
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("storr_ID", params.get("storr_id"));
        valueMap.put("original_ID", params.get("original_id"));

        personalDetails.getBirthByDeathId(valueMap);
        personalDetails.getDeathByDeathId(valueMap);
        personalDetails.getMarriageByDeathId(valueMap);

        return personalDetails;
    }


    @Override
    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception {
        return null;
    }

    @Override
    public void getParams(Map<String, String> map) {
        Map<String,String> setMap = new HashMap<>();
        setMap.put("standardised_ID", map.get("standardised_id"));
        setMap.put("storr_ID", map.get("storr_id"));
        setMap.put("original_Id", map.get("original_id"));
    }

    public Map<String,String> getGender(Map<String, String> map) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth) ");
        query.append(GetBirthById.getIdAttribute(map));
        query.append(" RETURN b.SEX");
        detail = neo4jService.getPerson(query.toString());
        return detail;
    }

}
