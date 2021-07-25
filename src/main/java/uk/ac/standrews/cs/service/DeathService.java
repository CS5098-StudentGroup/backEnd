package uk.ac.standrews.cs.service;


import uk.ac.standrews.cs.pojo.Death;

import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author Dongyao Liu
 * @Description 服务接口
 **/
public interface DeathService {
    String findData (String surName, String foreName, String gender, String dateOfBirth, String deathDay, String deathMonth, String deathYear);
}
