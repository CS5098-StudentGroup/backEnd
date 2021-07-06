package com.burt.Entity;

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
@Node("Birth")
public class Birth {
    @Id
    @GeneratedValue
    public Long id;

    @Property("SURNAME")
    String surName;

    @Property("FORENAME")
    String foreName;

    @Property("BIRTH_YEAR")
    String birthYear;

    @Property("BIRTH_MONTH")
    String birthMonth;

    @Property("BIRTH_DAY")
    String birthDay;
}
