package uk.ac.standrews.cs.Pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-31 16:58
 **/

@Data
public class Person {
    @Autowired
    Category c;
    String name;
    String gender;
    String category;

    //通过枚举调取category的类型
    public String getCategory(String Category) {
        switch (c.relationValue) {
            case "1":
        }
        return null;
    }
}
