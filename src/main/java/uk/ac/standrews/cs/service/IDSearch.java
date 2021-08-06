package uk.ac.standrews.cs.service;

import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;

import java.util.Map;
public interface IDSearch {

    FamilyTree setTree(Map<String, String> params) throws Exception;

    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;

    public void getParams(Map<String, String> map);

    PersonalDetails setDetails(Map<String, String> params) throws Exception;
}
