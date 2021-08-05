package uk.ac.standrews.cs.Pojo.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-08-04 14:13
 **/

@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class DeathRecords {
    String deathDate;
    String age_at_death;
    String Deceased_Identity;
    String death_StorrID;
    String Marital_Status;
    String Death_Place;
    String DeathRegistration_Year;
    String death_StandardisedID;
    String Birth_Record_Identity;
}
