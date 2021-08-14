package uk.ac.standrews.cs.Pojo.familyTree;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.service.Details.GetDeathInfo;
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
    @Autowired
    GetDeathInfo getDeathInfo;

    List<NodePointer> pointerList;
    List<Person> personList;
    List<Category> categoryList;

    public FamilyTree() {
        pointerList = new ArrayList<>();
        personList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }


    public void getMember(Map<String, String> valueMap) throws Exception {
        personList.clear();
        //self
        personList.add(getInfo.getSelf(valueMap));
        //mother
        if(getInfo.getMother(valueMap).getName() != null) {personList.add(getInfo.getMother(valueMap));}
        //bride
        if(!getInfo.getBride(valueMap).isEmpty() && valueMap.get("gender").equals("M")) {personList.addAll(getInfo.getBride(valueMap));}
        //groom
        if(!getInfo.getGroom(valueMap).isEmpty() && valueMap.get("gender").equals("F")) {personList.addAll(getInfo.getGroom(valueMap));}
        //father
        if(getInfo.getFather(valueMap) != null) {personList.add(getInfo.getFather(valueMap));}
        //siblings
        if(!getInfo.getSiblings(valueMap).isEmpty()) {personList.addAll(getInfo.getSiblings(valueMap));}
        if(!getInfo.getChildren(valueMap).isEmpty()) {personList.addAll(getInfo.getChildren(valueMap));}

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

        if(getInfo.getFather(valueMap) != null) {pointerList.add(nodePointer.toFather(valueMap));}
        if(getInfo.getMother(valueMap).getName() != null) {pointerList.add(nodePointer.toMother(valueMap));}
        if(!getInfo.getBride(valueMap).isEmpty() && valueMap.get("gender").equals("M")) {pointerList.addAll(nodePointer.toBride(valueMap));}
        if(!getInfo.getGroom(valueMap).isEmpty() && valueMap.get("gender").equals("F")) {pointerList.addAll(nodePointer.toGroom(valueMap));}
        if(!getInfo.getSiblings(valueMap).isEmpty()) {pointerList.addAll(nodePointer.toSibling(valueMap));}
        if(!getInfo.getChildren(valueMap).isEmpty()) {pointerList.addAll(nodePointer.toChildren(valueMap));}
    }


    public void getDeathMember(Map<String, String> valueMap) throws Exception {
        personList.clear();
        //self
        personList.add(getDeathInfo.getSelf(valueMap));
        //bride
        if(!getDeathInfo.getBride(valueMap).isEmpty() && valueMap.get("gender").equals("M")) {personList.addAll(getDeathInfo.getBride(valueMap));}
        //groom
        if(!getDeathInfo.getGroom(valueMap).isEmpty() && valueMap.get("gender").equals("F")) {personList.addAll(getDeathInfo.getGroom(valueMap));}

        //siblings
        if(!getDeathInfo.getSiblings(valueMap).isEmpty()) {personList.addAll(getDeathInfo.getSiblings(valueMap));}
    }

    public void getDeathPointer(Map<String, String> valueMap) throws Exception {
        pointerList.clear();

        if(!getDeathInfo.getBride(valueMap).isEmpty() && valueMap.get("gender").equals("M")) {pointerList.addAll(nodePointer.toDeathBride(valueMap));}
        if(!getDeathInfo.getGroom(valueMap).isEmpty() && valueMap.get("gender").equals("F")) {pointerList.addAll(nodePointer.toDeathGroom(valueMap));}
        if(!getDeathInfo.getSiblings(valueMap).isEmpty()) {pointerList.addAll(nodePointer.toDeathSibling(valueMap));}
    }
}
