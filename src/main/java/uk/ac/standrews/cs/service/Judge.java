package uk.ac.standrews.cs.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;
import java.util.Map;
public interface Judge {
    StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception;

    StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;

    FamilyTree setTree(Map<String, String> params) throws Exception;

    void getParams(Map<String, String> map);

    PersonalDetails setDetails(Map<String, String> params) throws Exception;
}

