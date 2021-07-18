package uk.ac.standrews.cs.pojo;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;


@AllArgsConstructor
@NoArgsConstructor
@Node("Death")
@Data
public class Death {
    @Id
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
