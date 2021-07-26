package uk.ac.standrews.cs.service;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Neo4jService {
    public StringBuilder printJson(String query) throws  Exception;
}
