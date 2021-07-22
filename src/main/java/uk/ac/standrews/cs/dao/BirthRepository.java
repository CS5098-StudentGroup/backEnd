package uk.ac.standrews.cs.dao;

import org.springframework.stereotype.Repository;
import uk.ac.standrews.cs.pojo.Birth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface BirthRepository extends Neo4jRepository<Birth, Long> {

    //根据surname查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.SURNAME=$surName RETURN p ")
    List<Birth> findBySurName(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                              @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);

    //根据forename查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.FORENAME=$foreName RETURN p")
    List<Birth> findByForeName(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                              @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);

    //根据gender查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.FORENAME=$foreName RETURN p")
    List<Birth> findBySex(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                               @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);

    //根据death date查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.BIRTH_DAY=$birthDay AND p.BIRTH_MONTH=$birthMonth AND p.BIRTH_YEAR=$birthYear RETURN p")
    List<Birth> findByDate(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                          @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据foreName and surName 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.FORENAME=$foreName AND p.SURNAME=$surName RETURN p")
    List<Birth> findByName(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                          @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据gender and surName 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.SEX=$gender AND p.SURNAME=$surName RETURN p")
    List<Birth> findBySurSex(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                          @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据foreName and gender 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.FORENAME=$foreName AND p.SEX=$gender RETURN p")
    List<Birth> findByForSex(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                             @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);

    //根据foreName surName gender 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.FORENAME=$foreName AND p.SEX=$gender AND p.SURNAME=$surName RETURN p")
    List<Birth> findByAllSex(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                             @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据surName date 查找关于Death的详细数据
    @Query("MATCH (p:Death) WHERE p.SURNAME=$surName AND p.BIRTH_DAY=$birthDay AND p.BIRTH_MONTH=$birthMonth AND p.BIRTH_YEAR=$birthYear RETURN p")
    List<Birth> findBySurDate(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                             @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据foreName date 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.FORENAME=$foreName AND p.BIRTH_DAY=$birthDay AND p.BIRTH_MONTH=$birthMonth AND p.BIRTH_YEAR=$birthYear RETURN p")
    List<Birth> findForDate(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                              @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据foreName surName date 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.SURNAME=$surName AND p.FORENAME=$foreName AND p.BIRTH_DAY=$birthDay AND p.BIRTH_MONTH=$birthMonth AND p.BIRTH_YEAR=$birthYear RETURN p")
    List<Birth> findFSDate(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                            @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


    //根据foreName surName gender date 查找关于Death的详细数据
    @Query("MATCH (p:Birth) WHERE p.SEX=$gender AND p.SURNAME=$surName AND p.FORENAME=$foreName AND p.BIRTH_DAY=$birthDay AND p.BIRTH_MONTH=$birthMonth AND p.BIRTH_YEAR=$birthYear RETURN p")
    List<Birth> findAllDate(@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender,
                           @Param("birthDay") String birthDay, @Param("birthMonth") String birthMonth, @Param("birthYear") String birthYear);


}