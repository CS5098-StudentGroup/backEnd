package uk.ac.standrews.cs.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

   public void getMember(Map<String, String> valueMap) {
       //self
       if(getInfo.getSelf(valueMap).name != null) {personList.add(getInfo.getSelf(valueMap));}
       //mother
       if(getInfo.getMother(valueMap).name != null) {personList.add(getInfo.getMother(valueMap));}
       //bride
       if(getInfo.getBride(valueMap).name != null && valueMap.get("gender").equals("M")) {personList.add(getInfo.getBride(valueMap));}
       //groom
       if(getInfo.getBride(valueMap).name != null && valueMap.get("gender").equals("F")) {personList.add(getInfo.getGroom(valueMap));}
       //father
       if(getInfo.getFather(valueMap).name != null) {personList.add(getInfo.getFather(valueMap));}
       //siblings
       if(getInfo.getSiblings(valueMap).name != null) {
           personList.add(getInfo.getSiblings(valueMap));
       }
   }

   public void getCategory() {
       categoryList.add(Category.self);
       categoryList.add(Category.father);
       categoryList.add(Category.mother);
       categoryList.add(Category.siblings);
       categoryList.add(Category.bride);
       categoryList.add(Category.groom);
   }

   public void getPointer(Map<String, String> valueMap){
       if(getInfo.getFather(valueMap).name != null) {pointerList.add(nodePointer.toFather(valueMap));}
       if(getInfo.getMother(valueMap).name != null) {pointerList.add(nodePointer.toMother(valueMap));}
       if(getInfo.getBride(valueMap).name != null && valueMap.get("gender").equals("M")) {pointerList.add(nodePointer.toBride(valueMap));}
       if(getInfo.getGroom(valueMap).name != null && valueMap.get("gender").equals("F")) {pointerList.add(nodePointer.toGroom(valueMap));}
       if(getInfo.getSiblings(valueMap).name != null) {pointerList.add(nodePointer.toSibling(valueMap));}
   }

/*   public StringBuilder links(List<NodePointer> pointer,List<Category> category, List<Person> person) {
       StringBuilder link = new StringBuilder();
       link.append(pointer);
       link.append(category);
       link.append(person);
       System.out.println(link.toString());
       return link;
   }*/
}
