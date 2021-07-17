package uk.ac.standrews.cs.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.ac.standrews.cs.pojo.Death;

import java.util.List;

@Repository
public interface DeathRepository extends Neo4jRepository<Death, Long> {
    @Query("MATCH (p1:Death ) where p1.SURNAME=$surName AND p1.FORENAME=$foreName return p1")
    List<Death> findByName(@Param("surName")String surName, @Param("foreName") String foreName);
}
