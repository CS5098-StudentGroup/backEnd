package uk.ac.standrews.cs.service;

import java.util.Map;

public interface Judge {
    public StringBuilder getFinalJson(Map<String, String> valueMap) throws Exception;

    public StringBuilder setJson(Map<String, String> map, Map<String, String> params) throws Exception;
}
