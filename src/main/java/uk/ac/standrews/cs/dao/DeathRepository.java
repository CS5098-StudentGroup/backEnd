package uk.ac.standrews.cs.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.standrews.cs.pojo.Death;
import java.util.List;

@Repository
public interface DeathRepository extends Neo4jRepository<Death, Long> {
        //查找关于Death的详细数据
        /*@Query("MATCH(p1:Death) WHERE p1.SURNAME=$surName AND p1.FORENAME=$foreName RETURN p1")*/
        @Query("MATCH(p1:Death) WHERE " +
                "CASE WHEN NOT $surName IS NULL " +
                "THEN p1.SURNAME=$surName  ELSE TRUE " +
                "END AND " +
                "CASE WHEN NOT $surName IS NULL" +
                "THEN p1.FORENAME=$foreName ELSE TRUE" +
                "END AND " +
                "CASE WHEN NOT $gender IS NULL " +
                "THEN p1.SEX = $gender ELSE TRUE " +
                "END RETURN p1")
        List<Death> findByName (@Param("surName") String surName, @Param("foreName") String foreName, @Param("gender") String gender, @Param("dateOfBirth") String dateOfBirth, @Param("dateOfDeath") String dateOfDeath, @Param("dateOfMarriage") String dateOfMarriage);
        /*Collection<PersonalDetails> getResult (@Param("surName") String surName, @Param("foreName") String foreName);*/

    /*@Query*/
}
