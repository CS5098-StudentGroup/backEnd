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


        @Query("MATCH(p1:Death) WHERE p1.SURNAME=$surName AND p1.FORENAME=$foreName RETURN p1")
        List<Death> findByName (@Param("surName") String surName, @Param("foreName") String foreName);
        /*Collection<PersonalDetails> getResult (@Param("surName") String surName, @Param("foreName") String foreName);*/

    /*@Query*/
}
