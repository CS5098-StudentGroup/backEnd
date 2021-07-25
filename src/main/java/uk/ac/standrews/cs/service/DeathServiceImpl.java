package uk.ac.standrews.cs.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.dao.DeathRepository;
import uk.ac.standrews.cs.pojo.Death;
import java.util.List;

/**
 * @Author Dongyao Liu
 * @Description Death服务实现
 **/
@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeathServiceImpl implements DeathService {

    @Autowired
    private DeathRepository deathRepository;
    int i = 0;
    String query1;

    @Override
    public String findData(String surName, String foreName, String gender, String dateOfBirth, String deathDay, String deathMonth, String deathYear) {
        StringBuilder q = new StringBuilder();
        q.append("MATCH (p:Death)");
        q.append(" WHERE");
        if( !surName.equals("") && i > 0) {
            q.append(" AND p.SURNAME=").append('"').append(surName).append('"'); // or could add these to params
        }
        if(!surName.equals("") && i == 0){i++; q.append(" p.SURNAME=").append('"').append(surName).append('"');  }

        if( !foreName.equals("") && i > 0) {
            q.append(" AND p.FORENAME=").append('"').append(foreName).append('"');
        }
        if(! foreName.equals("") && i == 0){i++; q.append(" p.FORENAME=").append('"').append(foreName).append('"'); }

        if(!(gender == null) && i> 0){
            q.append(" AND p.SEX=").append(gender);
        }
        if(!(gender == null) && i == 0){i++; q.append(" p.SEX=").append(gender); }
        q.append(" RETURN p.SURNAME AS surName, p.FORENAME AS foreName, p.");

        System.out.println(q.toString());
        i=0;
        query1 = q.toString();
        System.out.println(query1);
        return query1/*deathRepository.findBySurName(surName,foreName,gender,dateOfBirth,deathDay,deathMonth,deathYear,query1)*/;

        /*if (!surName.equals("") && foreName.equals("") && gender == null && deathDay==null && deathMonth==null && deathYear==null) {
             list = deathRepository.findBySurName(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }*/
        /*//foreName
        if (surName.equals("") && !foreName.equals("") && gender == null && deathDay==null && deathMonth==null && deathYear==null) {
            list = deathRepository.findByForeName(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //gender
        if (surName.equals("") && foreName.equals("") && !(gender == null) && deathDay==null && deathMonth==null && deathYear==null) {
            list = deathRepository.findBySex(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //deathDay
        if (surName.equals("") && foreName.equals("") && gender == null && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null)) {
            list = deathRepository.findByDay(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //surName + foreName
        if (!surName.equals("") && !foreName.equals("") && gender == null && deathDay==null && deathMonth==null && deathYear==null) {
            list = deathRepository.findByName(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //surName + gender
        if (!surName.equals("") && foreName.equals("") && !(gender == null) && deathDay==null && deathMonth==null && deathYear==null) {
            list = deathRepository.findBySurSex(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //foreName + gender
        if (surName.equals("") && !foreName.equals("") && !(gender == null) && deathDay==null && deathMonth==null && deathYear==null) {
            list = deathRepository.findByForSex(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //surName + foreName + gender
        if (!surName.equals("") && !foreName.equals("") && !(gender == null) && deathDay==null && deathMonth==null && deathYear==null) {
            list = deathRepository.findByAllSex(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //surName + date
        if (!surName.equals("") && foreName.equals("") && gender == null && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null)) {
            list = deathRepository.findBySurDate(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //foreName +date
        if (surName.equals("") && !foreName.equals("") && gender == null && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null)) {
            list = deathRepository.findForDate(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //foreName +surName + date
        if (!surName.equals("") && !foreName.equals("") && gender == null && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null)) {
            list = deathRepository.findFSDate(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        //foreName +surName + date + gender
        if (!surName.equals("") && !foreName.equals("") && !(gender == null) && !(deathDay ==null) && !(deathMonth ==null) && !(deathYear ==null)) {
            list = deathRepository.findAllDate(surName, foreName, gender, dateOfBirth, deathDay, deathMonth, deathYear, dateOfMarriage);
        }
        return list;*/
    }
}
