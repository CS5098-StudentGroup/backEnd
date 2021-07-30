package uk.ac.standrews.cs.service;

import java.util.Map;

public interface Judge {
    public StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception;
}
