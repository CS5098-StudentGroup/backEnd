package uk.ac.standrews.cs.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.neo4j.driver.*;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.neoStorr.util.NeoDbCypherBridge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @program: backEnd
 * @description: formatting json data
 * @author: Dongyao Liu
 * @create: 2021-07-25 21:08
 **/

@Service
public class Neo4jServiceImpl implements Neo4jService {

    @Override
    public StringBuilder printJson(String query) throws Exception {
            JSONObject js1 = new JSONObject();
            NeoDbCypherBridge bridge = new NeoDbCypherBridge();
            Session session = bridge.getNewSession();
            Result result = session.run(query);
            StringBuilder formatJson = new StringBuilder();
            StringBuilder finalData = new StringBuilder();
            while (result.hasNext()) {
                Record record = result.next();
                List<String> keys = record.keys();
                for (String key : keys) {
                    // keys1.get(i);
                    Value value = record.get(key);
                    String[] getValue = value.toString().split("\"");
                    if (getValue.length == 0) {
                        js1.put(key, "");
                    } else {
                        js1.put(key, getValue[getValue.length-1]);
                    }
                }
                if(!result.hasNext()){formatJson.append(js1);}
                else {formatJson.append(js1).append(",");}
            }
            finalData.append("[").append(formatJson).append("]");
            return finalData;
    }
}