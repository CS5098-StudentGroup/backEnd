package uk.ac.standrews.cs.Pojo;

import lombok.AllArgsConstructor;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-31 17:02
 **/

@AllArgsConstructor
public enum Category {
    father(1, "father"), mother(2, "mother"), siblings(3, "siblings"), bride(4, "bride"), groom(5,"groom");

    public final int number;
    public final String relationValue;

    @Override
    public String toString() {
        return relationValue;
    }
}
