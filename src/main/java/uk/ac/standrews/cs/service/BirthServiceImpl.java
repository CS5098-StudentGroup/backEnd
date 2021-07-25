package uk.ac.standrews.cs.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.pojo.Birth;
import uk.ac.standrews.cs.dao.BirthRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class BirthServiceImpl implements BirthService {

    @Autowired
    private BirthRepository birthRepository;
    int i=0;
    String query1;
    @Override
    public List<Birth> findData(String surName, String foreName, String gender, String birthDay, String birthMonth, String birthYear) {
        StringBuilder q = new StringBuilder();

        q.append("MATCH (p:Birth)");
        q.append(" WHERE");


        if( !surName.equals("") && i > 0) {
            q.append(" AND p.SURNAME=").append(surName); // or could add these to params
        }
        if(!surName.equals("") && i == 0){i++; q.append( " p.SURNAME="+surName);  }

        if( !foreName.equals("") && i > 0) {
            q.append(" AND p.FORENAME=").append(foreName);
        }
        if(! foreName.equals("") && i == 0){i++; q.append(" p.FORENAME=").append(foreName); }

        if(!(gender == null) && i> 0){
            q.append(" AND p.SEX=").append(gender);
        }
        if(!(gender == null) && i == 0){i++; q.append(" p.SEX=").append(gender); }

        if(!(birthDay == null) && !(birthMonth == null) && !(birthYear == null) && i>0){
            q.append(" AND p.BIRTH_DAY=").append(birthDay).append(" AND p.BIRTH_MONTH=").append(birthMonth).append(" AND p.BIRTH_YEAR=").append(birthYear);
        }
        if(!(birthDay == null) && !(birthMonth == null) && !(birthYear == null) && i==0){
            i++;
            q.append(" p.BIRTH_DAY=").append(birthDay).append(" AND p.BIRTH_MONTH=").append(birthMonth).append(" AND p.BIRTH_YEAR=").append(birthYear);

        }
        q.append(" RETURN p ");

        i=0;
        query1 = q.toString();
        return birthRepository.findByForeName(surName,foreName,gender,birthDay,birthMonth,birthYear);
        /*if (!surName.equals("") && foreName.equals("") && gender == null && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findBySurName(surName, foreName, gender, birthDay, birthMonth, birthYear);
        }
        //foreName
        if (surName.equals("") && !foreName.equals("") && gender == null && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findByForeName(surName, foreName, gender, birthDay, birthMonth, birthYear);
        }
        //gender
        if (surName.equals("") && foreName.equals("") && !(gender == null) && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findBySex(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //deathDay
        if (surName.equals("") && foreName.equals("") && gender == null && !(birthDay == null) && !(birthMonth == null) && !(birthYear == null)) {
            list = birthRepository.findByDate(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //surName + foreName
        if (!surName.equals("") && !foreName.equals("") && gender == null && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findByName(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //surName + gender
        if (!surName.equals("") && foreName.equals("") && !(gender == null) && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findBySurSex(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //foreName + gender
        if (surName.equals("") && !foreName.equals("") && !(gender == null) && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findByForSex(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //surName + foreName + gender
        if (!surName.equals("") && !foreName.equals("") && !(gender == null) && birthDay == null && birthMonth == null && birthYear == null) {
            list = birthRepository.findByAllSex(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //surName + date
        if (!surName.equals("") && foreName.equals("") && gender == null && !(birthDay == null) && !(birthMonth == null) && !(birthYear == null)) {
            list = birthRepository.findBySurDate(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //foreName +date
        if (surName.equals("") && !foreName.equals("") && gender == null && !(birthDay == null) && !(birthMonth == null) && !(birthYear == null)) {
            list = birthRepository.findForDate(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //foreName +surName + date
        if (!surName.equals("") && !foreName.equals("") && gender == null && !(birthDay == null) && !(birthMonth == null) && !(birthYear == null)) {
            list = birthRepository.findFSDate(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        //foreName +surName + date + gender
        if (!surName.equals("") && !foreName.equals("") && !(gender == null) && !(birthDay == null) && !(birthMonth == null) && !(birthYear == null)) {
            list = birthRepository.findAllDate(surName, foreName, gender, birthDay,birthMonth,birthYear);
        }
        return list;*/
    }
}
