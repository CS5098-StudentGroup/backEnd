package uk.ac.standrews.cs.pojo;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.web.bind.annotation.CrossOrigin;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node("Birth")
public class Birth {
    @Id
    private Long id;

    @Property("SURNAME")
    private String surName;

    @Property("FORENAME")
    private String foreName;

    /*@Property("SEX")
    private  String sex;

    @Property("STORR_ID")
    private long storr_id;

    @Property("ORIGINAL_ID")
    private String original_id;

    @Property("BIRTH_YEAR")
    private String birthYear;

    @Property("BIRTH_MONTH")
    private String birthMonth;

    @Property("BIRTH_DAY")
    private String birthDay;*/
}
