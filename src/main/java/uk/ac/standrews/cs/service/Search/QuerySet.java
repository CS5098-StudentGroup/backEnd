package uk.ac.standrews.cs.service.Search;


import java.util.Map;

public interface QuerySet {
    public String getBirthDeathQuery(Map<String, String> map);

    public String getBirthBrideQuery(Map<String, String> map);

    public String getBirthGroomQuery(Map<String, String> map);

    public String getGroomQuery(Map<String, String> map);

    public String getBrideQuery(Map<String, String> map);

    public String addPeopleNotDie(Map<String, String> map);

    public String getDetailsAboutGroomAndBirth(Map<String, String> map);

    public String getDetailsAboutBrideAndBirth(Map<String, String> map);

    public String getDetailOfGroom(Map<String, String> map);

    public String getDetailOfBride(Map<String, String> map);

    public String getDeathGroom(Map<String, String> map);

    public String getDeathBride(Map<String, String> map);
}
