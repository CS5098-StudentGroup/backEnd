package uk.ac.standrews.cs.Pojo;

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

    //通过枚举调取category的类型
    public Category getCategory(int category) {
        switch (category) {
            case 0:
                c = Category.self; break;
            case 1:
                c = Category.father;break;
            case 2:
                c = Category.mother;break;
            case 3:
                c = Category.siblings; break;
            case 4:
                c = Category.bride; break;
            case 5:
                c = Category.groom; break;
            default: throw new RuntimeException("cannot process" + category);
        }
        return c;
    }
}