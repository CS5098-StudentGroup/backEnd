package uk.ac.standrews.cs.service;

public interface BirthDeathService {
    public String getQuery(String surName, String foreName, String gender,
                           String birthDay, String birthMonth, String birthYear,
                           String deathDay, String deathMonth, String deathYear);
}
