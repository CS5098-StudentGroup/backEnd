package uk.ac.standrews.cs.Pojo.familyTree;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.service.GetInfo;
import java.util.*;

/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-31 17:13
 **/
@Service
@Data
public class FamilyTree {

    @Autowired
    GetInfo getInfo;

    public FamilyTree() {
        pointerList = new ArrayList<>();
        personList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    List<NodePointer> pointerList;
    List<Person> personList;
    List<Category> categoryList;
    @Autowired
    NodePointer nodePointer;


    public List<Person> Family(Map<String, String> valueMap) {
        List<Person> family = new ArrayList<>();
        Person self = getInfo.getSelf(valueMap);
        Person father = getInfo.getFather(valueMap);
        Person mother = getInfo.getMother(valueMap);
        family.add(self);
        family.add(father);
        family.add(mother);
        return family;
    }





    public void getMember(Map<String, String> valueMap) {
        personList.clear();
        //self
        List<Person> familyList = Family(valueMap);
        if(familyList.get(0).name != null) {personList.add(familyList.get(0));}
        //mother
        if(familyList.get(2).name != null) {personList.add(familyList.get(2));}
        //bride
        if(getInfo.getBride(valueMap).name != null && valueMap.get("gender").equals("M")) {personList.add(getInfo.getBride(valueMap));}
        //groom
        if(getInfo.getBride(valueMap).name != null && valueMap.get("gender").equals("F")) {personList.add(getInfo.getGroom(valueMap));}
        //father
        if(familyList.get(1).name != null) {personList.add(familyList.get(1));}
        //siblings
        if(getInfo.getSiblings(valueMap).size() != 0) {personList.addAll(getInfo.getSiblings(valueMap));}
        familyList.clear();
    }

    public void getCategory() {
        categoryList.clear();
        categoryList.add(Category.self);
        categoryList.add(Category.father);
        categoryList.add(Category.mother);
        categoryList.add(Category.siblings);
        categoryList.add(Category.bride);
        categoryList.add(Category.groom);
    }

    public void getPointer(Map<String, String> valueMap){
        pointerList.clear();
        List<Person> familyList = Family(valueMap);
        if(familyList.get(1).name != null) {pointerList.add(nodePointer.toFather(valueMap));}
        if(familyList.get(2).name != null) {pointerList.add(nodePointer.toMother(valueMap));}
        if(getInfo.getBride(valueMap).name != null && valueMap.get("gender").equals("M")) {pointerList.add(nodePointer.toBride(valueMap));}
        if(getInfo.getGroom(valueMap).name != null && valueMap.get("gender").equals("F")) {pointerList.add(nodePointer.toGroom(valueMap));}
        if(getInfo.getSiblings(valueMap).size() != 0) {pointerList.addAll(nodePointer.toSibling(valueMap));}
        familyList.clear();
    }
}
