package uk.ac.standrews.cs.dao;

import org.springframework.stereotype.Repository;
import uk.ac.standrews.cs.pojo.Birth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


@Repository
public interface BirthRepository extends Neo4jRepository<Birth, Long> {
    @Query("MATCH (p:Birth) WHERE " +
            "CASE WHEN NOT $surName IS NULL " +
            "THEN p.SURNAME=$surName ELSE TRUE " +
            "END AND " +
            "CASE WHEN NOT $foreName IS NULL " +
            "THEN p.FORENAME=$foreName ELSE TRUE " +
            "END " +
            "RETURN p")
    List<Birth> findName(@Param("surName")String surName, @Param("foreName") String foreName);

}
