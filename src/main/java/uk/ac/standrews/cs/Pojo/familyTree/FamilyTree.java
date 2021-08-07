package uk.ac.standrews.cs.Pojo.familyTree;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.service.Details.GetInfo;
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
    NodePointer nodePointer;
    @Autowired
    GetInfo getInfo;

    List<NodePointer> pointerList;
    List<Person> personList;
    List<Category> categoryList;

    public FamilyTree() {
        pointerList = new ArrayList<>();
        personList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    public List<Person> Family(Map<String, String> valueMap) throws Exception {
        List<Person> family = new ArrayList<>();
        Person self = getInfo.getSelf(valueMap);
        Person father = getInfo.getFather(valueMap);
        Person mother = getInfo.getMother(valueMap);
        family.add(self);
        family.add(father);
        family.add(mother);
        return family;
    }

    public void getMember(Map<String, String> valueMap) throws Exception {
        personList.clear();
        //self
        List<Person> familyList = Family(valueMap);
        if(familyList.get(0).name != null) {personList.add(familyList.get(0));}
        //mother
        if(familyList.get(2).name != null) {personList.add(familyList.get(2));}
        //bride
        if(!getInfo.getBride(valueMap).isEmpty() && valueMap.get("gender").equals("M")) {personList.addAll(getInfo.getBride(valueMap));}
        //groom
        if(!getInfo.getGroom(valueMap).isEmpty() && valueMap.get("gender").equals("F")) {personList.addAll(getInfo.getGroom(valueMap));}
        //father
        if(familyList.get(1).name != null) {personList.add(familyList.get(1));}
        //siblings
        if(!getInfo.getSiblings(valueMap).isEmpty()) {personList.addAll(getInfo.getSiblings(valueMap));}
        if(!getInfo.getChildren(valueMap).isEmpty()) {personList.addAll(getInfo.getChildren(valueMap));}
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
        categoryList.add(Category.children);
    }

    public void getPointer(Map<String, String> valueMap) throws Exception {
        pointerList.clear();
        List<Person> familyList = Family(valueMap);
        if(familyList.get(1).name != null) {pointerList.add(nodePointer.toFather(valueMap));}
        if(familyList.get(2).name != null) {pointerList.add(nodePointer.toMother(valueMap));}
        if(!getInfo.getBride(valueMap).isEmpty() && valueMap.get("gender").equals("M")) {pointerList.addAll(nodePointer.toBride(valueMap));}
        if(!getInfo.getGroom(valueMap).isEmpty() && valueMap.get("gender").equals("F")) {pointerList.addAll(nodePointer.toGroom(valueMap));}
        if(!getInfo.getSiblings(valueMap).isEmpty()) {pointerList.addAll(nodePointer.toSibling(valueMap));}
        if(!getInfo.getChildren(valueMap).isEmpty()) {pointerList.addAll(nodePointer.toChildren(valueMap));}
        familyList.clear();
    }
}
