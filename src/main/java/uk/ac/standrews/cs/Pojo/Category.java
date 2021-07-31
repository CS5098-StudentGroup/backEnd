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
    father("1"), mother("2"), siblings("3"), bride("4"), groom("5");

    public final String relationValue;

    @Override
    public String toString() {
        return relationValue;
    }
}
