package uk.ac.standrews.cs.service.CommonTool;

import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import java.util.Map;
public interface Judge {
    StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception;

    StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;

    FamilyTree setTree(Map<String, String> params) throws Exception;


    PersonalDetails setDetails(Map<String, String> params) throws Exception;
}

