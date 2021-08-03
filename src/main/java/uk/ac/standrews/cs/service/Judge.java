package uk.ac.standrews.cs.service;

import uk.ac.standrews.cs.Pojo.FamilyTree;
import java.util.Map;
public interface Judge {
    public StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception;

    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;

    public FamilyTree setTree(Map<String, String> params);

    public void getParams(Map<String, String> map);
}

