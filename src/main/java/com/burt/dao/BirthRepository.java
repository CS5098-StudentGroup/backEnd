package com.burt.dao;

import com.burt.Entity.Birth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BirthRepository  extends Neo4jRepository<Birth, Long> {


    @Query("MATCH (p1:Birth ) where p1.FORENAME=$foreName return p1")
    List<Birth> findByForeName(@Param("foreName") String foreName);
    List<Birth> findByBirthYear(String birthYear);



}
