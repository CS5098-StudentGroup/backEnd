package uk.ac.standrews.cs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-31 16:58
 **/

@Data
@AllArgsConstructor
public class Person {
    String name;
    String gender;
    int category;
    Category c;
    //通过枚举调取category的类型
    public String getCategory(int category) {
        switch (category) {
            case 1:
                c = Category.father;break;
            case 2:
                c = Category.mother;break;
            case 3: break;
            case 4: break;
            case 5: break;
            default: throw new RuntimeException("cannot process" + category);
        }
        return null;
    }

    public void getPerson(String name, String gender, int category) {

    }

}
