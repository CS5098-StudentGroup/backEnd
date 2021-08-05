package uk.ac.standrews.cs.service;

import java.util.Map;
public interface IDSearch {


    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;

    public void getParams(Map<String, String> map);
}
