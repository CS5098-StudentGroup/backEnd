package uk.ac.standrews.cs.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("Death")
public class Death {
    @Id
    @GeneratedValue
    private Long id;

    @Property("SURNAME")
    private String surName;

    @Property("FORENAME")
    private String foreName;

    @Property("Death_YEAR")
    private String deathYear;

    @Property("DEATH_MONTH")
    private String deathMonth;

    @Property("DEATH_DAY")
    private String deathDay;

}
