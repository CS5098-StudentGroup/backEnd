package uk.ac.standrews.cs.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("Death")
public class Death {
    @Id
    @GeneratedValue
    private Long query_number;

    @Property("STORR_ID")
    private Long storr_id;

    @Property("ORIGINAL_ID")
    private String original_id;

    @Property("SURNAME")
    private String surName;

    @Property("FORENAME")
    private String foreName;

    @Property("DATE_OF_BIRTH")
    private String birth;

    @Property("Death_YEAR")
    private String deathYear;

    @Property("DEATH_MONTH")
    private String deathMonth;

    @Property("DEATH_DAY")
    private String deathDay;

    @Property("PLACE_OF_DEATH")
    private String deathPlace;


}
