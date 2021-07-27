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
        query.append("MATCH (b:Birth)");
        query.append("-[r:GROUND_TRUTH_BIRTH_DEATH_IDENTITY]-");
        query.append("(d:Death)");
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
        query.delete(query.length()-3, query.length());
        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, d.DATE_OF_BIRTH AS birthDate, d.DEATH_DAY AS deathDay, d.DEATH_MONTH AS deathMonth, d.DEATH_YEAR AS deathYear");
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