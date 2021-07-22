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
        //根据surname查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.SURNAME=$surName RETURN p ")
        List<Death> findBySurName (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                @Param("dateOfMarriage") String dateOfMarriage);

        //根据forename查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.FORENAME=$foreName RETURN p")
        List<Death> findByForeName (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                   @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                   @Param("dateOfMarriage") String dateOfMarriage);

        //根据gender查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.SEX=$gender RETURN p")
        List<Death> findBySex (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                    @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                    @Param("dateOfMarriage") String dateOfMarriage);

        //根据death date查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.DEATH_DAY=$deathDay AND p.DEATH_MONTH=$deathMonth AND p.DEATH_YEAR=$deathYear RETURN p")
        List<Death> findByDay (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                               @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                               @Param("dateOfMarriage") String dateOfMarriage);

        //根据foreName and surName 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.FORENAME=$foreName AND p.SURNAME=$surName RETURN p")
        List<Death> findByName (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                    @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                    @Param("dateOfMarriage") String dateOfMarriage);

        //根据gender and surName 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.SEX=$gender AND p.SURNAME=$surName RETURN p")
        List<Death> findBySurSex(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                @Param("dateOfMarriage") String dateOfMarriage);

        //根据foreName and gender 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.FORENAME=$foreName AND p.SEX=$gender RETURN p")
        List<Death> findByForSex (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                @Param("dateOfMarriage") String dateOfMarriage);

        //根据foreName surName gender 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.FORENAME=$foreName AND p.SEX=$gender AND p.SURNAME=$surName RETURN p")
        List<Death> findByAllSex (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                  @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                  @Param("dateOfMarriage") String dateOfMarriage);
        //根据surName date 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.SURNAME=$surName AND p.DEATH_DAY=$deathDay AND p.DEATH_MONTH=$deathMonth AND p.DEATH_YEAR=$deathYear RETURN p")
        List<Death> findBySurDate (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                               @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                               @Param("dateOfMarriage") String dateOfMarriage);

        //根据foreName date 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.FORENAME=$foreName AND p.DEATH_DAY=$deathDay AND p.DEATH_MONTH=$deathMonth AND p.DEATH_YEAR=$deathYear RETURN p")
        List<Death> findForDate (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                   @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                   @Param("dateOfMarriage") String dateOfMarriage);

        //根据foreName surName date 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.SURNAME=$surName AND p.FORENAME=$foreName AND p.DEATH_DAY=$deathDay AND p.DEATH_MONTH=$deathMonth AND p.DEATH_YEAR=$deathYear RETURN p")
        List<Death> findFSDate (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                 @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                 @Param("dateOfMarriage") String dateOfMarriage);

        //根据foreName surName gender date 查找关于Death的详细数据
        @Query("MATCH (p:Death) WHERE p.SEX=$gender AND p.SURNAME=$surName AND p.FORENAME=$foreName AND p.DEATH_DAY=$deathDay AND p.DEATH_MONTH=$deathMonth AND p.DEATH_YEAR=$deathYear RETURN p")
        List<Death> findAllDate (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth,
                                @Param("deathDay") String deathDay, @Param("deathMonth") String deathMonth, @Param("deathYear") String deathYear,
                                @Param("dateOfMarriage") String dateOfMarriage);
}
