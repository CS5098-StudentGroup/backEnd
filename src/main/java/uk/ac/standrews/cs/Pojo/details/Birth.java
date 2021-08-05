package uk.ac.standrews.cs.Pojo.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
/**
 * @program: backEnd
 * @description: BirthPojo
 * @author: Dongyao Liu
 * @create: 2021-08-04 14:12
 **/

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class Birth {
    String surName;
    String foreName;

}
