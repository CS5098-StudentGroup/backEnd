package uk.ac.standrews.cs.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: backEnd
 * @description: Processing dynamic query statements
 * @author: Dongyao Liu
 * @create: 2021-07-26 21:25
 **/
@Service
public class BirthDeathServiceIml implements BirthDeathService {

    //Get cypher and formatting it
    @Override
    public String getQuery(Map<String, String> map) {
        AtomicInteger i = new AtomicInteger();
        StringBuilder query = new StringBuilder();
        query.append("MATCH (d:Death)-[r:GROUND_TRUTH_DEATH_BIRTH_IDENTITY]->(b:Birth) ");
        query.append(" MATCH (b1:Birth)");
        query.append(" WHERE");
        removeEmptyMap(map);
        map.forEach((key, value) -> {
            if(key.contains("birth_Day")||key.contains("birth_Month")||key.contains("birth_Year")||key.contains("surName")||key.contains("foreName")||key.contains("sex")) {
                    query.append(" b.").append(key.toUpperCase()).append("=").append('"').append(value.toUpperCase()).append('"').append(" AND");
            }
            if(key.contains("death_Day")||key.contains("death_Month")||key.contains("death_Year")){
                query.append(" d.").append(key.toUpperCase()).append("=").append('"').append(value.toUpperCase()).append('"').append(" AND");
            }
        });
        query.append(" b.SURNAME=b1.SURNAME AND b.FORENAME=b1.FORENAME AND b.BIRTH_DAY=b1.BIRTH_DAY AND b.BIRTH_MONTH=b1.BIRTH_MONTH AND b.BIRTH_YEAR=b.BIRTH_YEAR AND");
        query.delete(query.length()-3, query.length());                      //      deathDay/deathMonth/deathYear
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, b.BIRTH_DAY+'/'+b.BIRTH_MONTH+'/'+b.BIRTH_YEAR AS birthDate, d.DEATH_DAY+'/'+d.DEATH_MONTH+'/'+d.DEATH_YEAR AS deathDate");
        System.out.println("Query is: " + query);
        return query.toString();
    }


    public static void removeEmptyMap(Map<String, String> maps) {
        Set<String> set = maps.keySet();
        Iterator<String> it = set.iterator();
        List<String> listKey = new ArrayList<>();
        while (it.hasNext()) {
            String str = it.next();
            if (maps.get(str) == null || "".equals(maps.get(str))) {
                listKey.add(str);
            }
        }
        for (String key : listKey) {
            maps.remove(key);
        }
    }

}