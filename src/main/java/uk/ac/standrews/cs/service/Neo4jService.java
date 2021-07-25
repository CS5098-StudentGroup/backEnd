package uk.ac.standrews.cs.service;

import org.json.JSONObject;

public interface Neo4jService {
    public JSONObject printJson(String query) throws  Exception;
}
