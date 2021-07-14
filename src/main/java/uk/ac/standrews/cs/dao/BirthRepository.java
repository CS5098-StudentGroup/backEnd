package uk.ac.standrews.cs.dao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.Mapping;
import uk.ac.standrews.cs.pojo.Birth;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface BirthRepository extends Neo4jRepository<Birth, Long> {

    //根据foreName查找自身的出生记录
    @Query("MATCH (p1:Birth ) where p1.FORENAME=$foreName return p1")
    List<Birth> findByForeName(@Param("foreName") String foreName);

    //根据surName查找出生记录
    @Query("MATCH (p1:Birth ) where p1.SURNAME=$surName return p1")
    List<Birth> findBySurName(@Param("surName") String surName);

    //根据foreName和surName精确查找
    @Query(value = "MATCH (p1:Birth ) where p1.SURNAME=$surName AND p1.FORENAME=$foreName return p1")
    List<Birth> findByName(@Param("surName")String surName, @Param("foreName") String foreName);
}
