package uk.ac.standrews.cs.service;

import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import java.util.HashMap;
import java.util.Map;
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

    StringBuilder groomCypher;
    StringBuilder brideCypher;
    StringBuilder finalJson = new StringBuilder();
    public static final String EMPTY = "empty";
    Map<String, String> valueMap = new HashMap<>();

    @Override
    public PersonalDetails setDetails(Map<String, String> params) throws Exception {
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("storr_ID", params.get("storr_id"));
        valueMap.put("original_ID", params.get("original_id"));

        personalDetails.getBirthById(valueMap);
        personalDetails.getDeathById(valueMap);
        personalDetails.getMarriageById(valueMap);

        return personalDetails;
    }

    @Override
    public FamilyTree setTree(Map<String, String> params) throws Exception {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("standardised_ID", params.get("standardised_id"));
        valueMap.put("gender", params.get("gender"));
        familyTree.getMember(valueMap);
        familyTree.getCategory();
        familyTree.getPointer(valueMap);
        return familyTree;
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

}
