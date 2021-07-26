package uk.ac.standrews.cs.service;

import org.springframework.stereotype.Service;

/**
 * @program: backEnd
 * @description: d
 * @author: Dongyao Liu
 * @create: 2021-07-26 21:25
 **/
@Service
public class BirthDeathServiceIml implements BirthDeathService{

    int i = 0;
    @Override
    public String getQuery(String surName, String foreName, String gender, String birthDay, String birthMonth, String birthYear, String deathDay, String deathMonth, String deathYear) {
        StringBuilder query = new StringBuilder();
        query.append("MATCH (b:Birth)");
        query.append("-[r:GROUND_TRUTH_BIRTH_DEATH_IDENTITY]-");
        query.append("(d:Death)");
        query.append(" WHERE");

        //surName
        if( !surName.equals("") && i > 0) {
            query.append(" AND b.SURNAME=").append('"').append(surName)
                    .append('"').append(" AND b.SURNAME=d.SURNAME");
        }
        if(!surName.equals("") && i == 0){i++; query.append(" b.SURNAME=")
                .append("'").append(surName).append("'").append(" AND b.SURNAME=d.SURNAME");;  }

        //foreName
        if( !foreName.equals("") && i > 0) {
            query.append(" AND b.FORENAME=")
                    .append('"').append(foreName).append('"').append(" AND b.FORENAME=d.FORENAME");
        }
        if(! foreName.equals("") && i == 0){i++; query.append(" b.FORENAME=").append('"').append(foreName).append('"').append(" AND b.FORENAME=d.FORENAME"); }

        //gender
        if(!(gender == null) && i> 0){
            query.append(" AND b.SEX=").append('"').append(gender).append('"');
        }
        if(!(gender == null) && i == 0){
            i++; query.append(" b.SEX=").append('"').append(gender).append('"');
        }

        //deathDay
        if (!(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null) && i>0) {
            query.append(" AND d.DEATH_DAY=").append('"').append(deathDay).append('"')
                    .append(" AND d.DEATH_MONTH=").append('"').append(deathMonth).append('"')
                    .append(" AND d.DEATH_YEAR").append('"').append(deathYear).append('"');
        }
        if (!(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null) && i==0) {
            i++;
            query.append(" d.DEATH_DAY=").append('"').append(deathDay).append('"')
                    .append(" AND d.DEATH_MONTH=").append('"').append(deathMonth).append('"')
                    .append(" AND d.DEATH_YEAR=").append('"').append(deathYear).append('"');
        }

        //birthday
        if (!(birthDay ==null) && !(birthMonth ==null) && !(birthYear ==null) && i>0) {
            query.append(" AND b.BIRTH_DAY=").append('"').append(birthDay).append('"')
                    .append(" AND b.BIRTH_MONTH=").append('"').append(birthMonth).append('"')
                    .append(" AND b.BIRTH_YEAR=").append('"').append(birthYear).append('"');
        }
        if (!(birthDay ==null) && !(birthMonth ==null) && !(birthYear ==null) && i==0) {
            i++;
            query.append(" b.BIRTH_DAY=").append('"').append(birthDay).append('"')
                    .append(" AND b.BIRTH_MONTH=").append('"').append(birthMonth).append('"')
                    .append(" AND b.BIRTH_YEAR=").append('"').append(birthYear).append('"');
        }

        query.append(" RETURN b.SURNAME AS surName, b.FORENAME AS foreName, b.SEX AS gender, d.DATE_OF_BIRTH AS birthDate");
        System.out.println("Query is: " + query);
        i=0;
        return query.toString();
    }
}
