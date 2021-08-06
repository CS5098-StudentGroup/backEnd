package uk.ac.standrews.cs.service.GetID;

import uk.ac.standrews.cs.Pojo.details.PersonalDetails;
import uk.ac.standrews.cs.Pojo.familyTree.FamilyTree;

import java.util.Map;
public interface IDSearch {

    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;

    public void getParams(Map<String, String> map);

    PersonalDetails setDetailsByBirthId(Map<String, String> params) throws Exception;

    PersonalDetails setDetailsByDeathId(Map<String, String> params) throws Exception;


}
