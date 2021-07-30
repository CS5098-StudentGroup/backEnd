package uk.ac.standrews.cs.service;


import java.util.Map;

public interface QuerySet {
    public String getBirthDeathQuery(Map<String, String> map);

    public String getBirthBrideQuery(Map<String, String> map);

    public String getBirthGroomQuery(Map<String, String> map);

    public String getGroomQuery(Map<String, String> map);

    public String getBrideQuery(Map<String, String> map);

}
