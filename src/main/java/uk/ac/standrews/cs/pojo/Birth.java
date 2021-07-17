package uk.ac.standrews.cs.pojo;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("Birth")
public class Birth {
    @Id
    @GeneratedValue
    private Long id;

    @Property("SURNAME")
    private String surName;

    @Property("FORENAME")
    private String foreName;

    @Property("STORR_ID")
    private long storr_id;

    @Property("ORIGINAL_ID")
    private String original_id;

    @Property("BIRTH_YEAR")
    private String birthYear;

    @Property("BIRTH_MONTH")
    private String birthMonth;

    @Property("BIRTH_DAY")
    private String birthDay;
}
