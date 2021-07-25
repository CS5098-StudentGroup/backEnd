package uk.ac.standrews.cs.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.neo4j.driver.*;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.neoStorr.util.NeoDbCypherBridge;

import java.util.List;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-25 21:08
 **/

@Slf4j
@Service
public class Neo4jServiceImpl implements Neo4jService {
    @Override
    public JSONObject printJson(String query) throws Exception {
        Driver driver = null;
        JSONObject js1=new JSONObject();
        NeoDbCypherBridge bridge = new NeoDbCypherBridge();
        try (Session session = bridge.getNewSession()) {
            try {
                Result result = session.run(query);
                System.out.println(result.toString());
                while (result.hasNext()) {
                    Record record = result.next();
                    List<String> keys1 = record.keys();
                    for (int i = 0; i < keys1.size(); i++) {
                        // keys1.get(i);
                        Value value = record.get(keys1.get(i));
                        js1.put(keys1.get(i), value);
                        System.out.println(js1);
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception in query: " + e.getMessage());
            }
            return js1;
        }
    }
}
