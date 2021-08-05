package uk.ac.standrews.cs.Pojo.details;

import lombok.*;
import org.springframework.stereotype.Service;

/**
 * @program: backEnd
 * @description: BirthPojo
 * @author: Dongyao Liu
 * @create: 2021-08-04 14:12
 **/

@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BirthRecords {
    String surName;
    String foreName;
    String gender;
    String birthDate;
    String standardised_ID;
    String Address;
    String birth_Storr_ID;
    String birth_OriginalID;
    String Changed_foreName;
    String Changed_surName;
    String Child_identity;
    String Father_foreName;
    String Father_surName;
    String Father_occupation;
    String Mother_identity;
    String Mother_surName;
    String Mother_foreName;
    String Mother_occupation;
    String Marriage_record_identity1;
    String Marriage_record_identity2;
    String Marriage_record_identity3;
    String Adoption;
    String Father_Identity;
    String Death;
}
