package com.burt.dao;

import com.burt.impl.Birth;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BirthRepository  extends Neo4jRepository<Birth, Long> {

    @Query("MATCH (a:Birth) WHERE a.FORENAME= \"ERIK\" RETURN a")
    public List<Birth> findByName(@Param("FORENAME") String forName);


}
