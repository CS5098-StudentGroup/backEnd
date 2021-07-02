package com.burt.impl;

import jdk.jfr.DataAmount;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import javax.annotation.sql.DataSourceDefinition;

@Node(labels = "STORR_LXP")

public class Birth {
    @Id
    @GeneratedValue
    public Long identity;

    @Property("SURNAME")
    String surName;

    @Property("FORENAME")
    String foreName;

    @Property("BIRTH_YEAR")
    String birthYear;


    public Birth() {
    }

    public Birth(Long identity, String surName, String foreName, String birthYear) {
        this.identity = identity;
        this.surName = surName;
        this.foreName = foreName;
        this.birthYear = birthYear;
    }

    public Long getIdentity() {
        return identity;
    }

    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }
}
