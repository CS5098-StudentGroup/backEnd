package uk.ac.standrews.cs.service;


import uk.ac.standrews.cs.pojo.Death;

import java.util.List;

/**
 * @Author Dongyao Liu
 * @Description 服务接口
 **/
public interface DeathService {
    List<Death> findData (String surName, String foreName, String gender, String dateOfBirth, String deathDay, String deathMonth, String deathYear, String dateOfMarriage);
}
