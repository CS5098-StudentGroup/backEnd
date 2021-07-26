package uk.ac.standrews.cs.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author Dongyao Liu
 * @Description Query death information in death node
 **/
@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeathServiceImpl implements DeathService {

    int i = 0;
    String query1;

    @Override
    public String getQuery(String surName, String foreName, String gender, String dateOfBirth, String deathDay, String deathMonth, String deathYear) {
        StringBuilder q = new StringBuilder();
        q.append("MATCH (p:Death)");
        q.append(" WHERE");
        if( !surName.equals("") && i > 0) {
            q.append(" AND p.SURNAME=").append('"').append(surName).append('"'); // or could add these to params
        }
        if(!surName.equals("") && i == 0){i++; q.append(" p.SURNAME=").append("'").append(surName).append("'");  }

        if( !foreName.equals("") && i > 0) {
            q.append(" AND p.FORENAME=").append('"').append(foreName).append('"');
        }
        if(! foreName.equals("") && i == 0){i++; q.append(" p.FORENAME=").append('"').append(foreName).append('"'); }

        if(!(gender == null) && i> 0){
            q.append(" AND p.SEX=").append('"').append(gender).append('"');
        }
        if(!(gender == null) && i == 0){
            i++; q.append(" p.SEX=").append('"').append(gender).append('"');
        }

        if (surName.equals("") && foreName.equals("") && gender == null && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null) && i>0) {
            q.append(" AND p.DEATH_DAY=").append('"').append(deathDay).append('"')
                    .append(" AND p.DEATH_MONTH=").append('"').append(deathMonth).append('"')
                    .append(" AND p.DEATH_YEAR").append('"').append(deathYear).append('"');
        }
        if (surName.equals("") && foreName.equals("") && gender == null && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null) && i==0) {
            i++;
            q.append(" p.DEATH_DAY=").append('"').append(deathDay).append('"')
                    .append(" AND p.DEATH_MONTH=").append('"').append(deathMonth).append('"')
                    .append(" AND p.DEATH_YEAR=").append('"').append(deathYear).append('"');
        }

        q.append(" RETURN p.SURNAME AS surName, p.FORENAME AS foreName, p.SEX AS gender, p.DATE_OF_BIRTH AS birthDate");
        System.out.println("Query is: " + q);
        i=0;
        query1 = q.toString();
        return query1;
    }
}
