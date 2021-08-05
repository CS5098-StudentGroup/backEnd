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
 * @create: 2021-08-04 14:12
 **/
@Getter
@Setter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class MarriageRecords {
   String SPOUSE_FORENAME;
   String SPOUSE_SURNAME;
   String SPOUSE_ADDRESS;
   String marriageDate;
   String MarriagePlace;
   String marriage_StandardisedID;
   String marriage_StorrID;
   String SPOUSE_IDENTITY;
   String SPOUSE_OCCUPATION;
   String SPOUSE_BIRTH_RECORD_IDENTITY;
   String SPOUSE_MARITAL_STATUS;
   String MarriageRegistration_Year;
   String SPOUSE_FATHER_DECEASED;
   String SPOUSE_FATHER_FORENAME;
   String SPOUSE_FATHER_SURNAME;
   String SPOUSE_FATHER_IDENTITY;
   String SPOUSE_FATHER_OCCUPATION;
   String SPOUSE_MOTHER_DECEASED;
   String SPOUSE_MOTHER_FORENAME;
   String SPOUSE_MOTHER_MAIDEN_SURNAME;
   String SPOUSE_MOTHER_IDENTITY;



}
