package uk.ac.standrews.cs.pojo;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Death {

    @Id
    @GeneratedValue
    private Long death_ID;

    @Property("STORR_ID")
    private Long storr_id;

    @Property("ORIGINAL_ID")
    private String original_id;

    @Property("SURNAME")
    private String surName;

    @Property("FORENAME")
    private String foreName;

    @Property("SEX")
    private String gender;

    @Property("AGE_AT_DEATH")
    private String age_death;

    @Property("DEATH_YEAR")
    private String deathYear;

    @Property("DEATH_MONTH")
    private String deathMonth;

    @Property("DEATH_DAY")
    private String deathDay;

    @Property("PLACE_OF_DEATH")
    private String deathPlace;

    @Property("DATE_OF_BIRTH")
    private String birthDate;
}
