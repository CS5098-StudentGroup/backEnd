package uk.ac.standrews.cs.Pojo.familyTree;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-31 16:58
 **/


@Service
@NoArgsConstructor
public class Person {
    String name;
    String gender;
    int category;
    Category c;

    public Person(String name, String gender, int category) {
        this.name = name;
        this.gender = gender;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setC(Category c) {
        this.c = c;
    }

}