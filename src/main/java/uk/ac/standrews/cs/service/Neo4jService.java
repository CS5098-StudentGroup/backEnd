package uk.ac.standrews.cs.service;

import java.util.Map;

public interface Neo4jService {
    public StringBuilder printJson(String query) throws Exception;

    public Map<String, String> getPerson(String query);
}
