package uk.ac.standrews.cs.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.standrews.cs.pojo.Death;
import java.util.List;

/**
 * @Author Dongyao Liu
 * @Description neo4j查询控制语句
 **/
@Repository
public interface DeathRepository extends Neo4jRepository<Death, Long> {
        //查找关于Death的详细数据
        @Query("MATCH(p1:Death) WHERE " +
                "CASE WHEN NOT $surName IS NULL " +
                "THEN p1.SURNAME=$surName ELSE TRUE " +
                "END AND " +
                "CASE WHEN NOT $foreName IS NULL " +
                "THEN p1.FORENAME=$foreName ELSE TRUE " +
                "END AND " +
                "CASE WHEN NOT $gender IS NULL " +
                "THEN p1.SEX = $gender ELSE TRUE " +
                "END AND " +
                "CASE WHEN NOT $deathDay IS NULL " +
                "THEN p1.DEATH_DAY=$deathDay ELSE TRUE " +
                "END AND " +
                "CASE WHEN NOT $deathMonth IS NULL " +
                "THEN p1.DEATH_MONTH=$deathMonth ELSE TRUE " +
                "END AND " +
                "CASE WHEN NOT $deathYear IS NULL " +
                "THEN p1.DEATH_YEAR=$deathYear ELSE TRUE " +
                "END " +
                "RETURN p1 ")
        List<Death> findByName (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                @Param("dateOfMarriage") String dateOfMarriage);
}
