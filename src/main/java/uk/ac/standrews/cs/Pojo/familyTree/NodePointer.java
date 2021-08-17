package uk.ac.standrews.cs.Pojo.familyTree;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.standrews.cs.service.Details.GetDeathInfo;
import uk.ac.standrews.cs.service.Details.GetInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @program: backEnd
 * @description:
 * @author: Dongyao Liu
 * @create: 2021-07-31 17:14
 **/


@Service
@Getter
@Setter
@NoArgsConstructor
public class NodePointer {
    @Autowired
    GetInfo getInfo;
    @Autowired
    GetDeathInfo getDeathInfo;
    String source;
    String target;

    public NodePointer(String source, String target) {
        this.source = source;
        this.target = target;
    }



    // define target as self
    // source as mother
    public NodePointer toMother(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        System.out.println(target);
        source = getInfo.getMother(valueMap).name;
        return new NodePointer(source,target);
    }

    // define target as self
    // source as father
    public NodePointer toFather(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        source = getInfo.getFather(valueMap).name;
        return new NodePointer(source,target);
    }

    // define target as self
    // source as bride
    public List<NodePointer> toBride(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getBride(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    // define target as self
    // source as groom
    public List<NodePointer> toGroom(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getGroom(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    // define target as self
    // source as siblings
    public List<NodePointer> toSibling(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getSiblings(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    // define target as self
    // source as children
    public List<NodePointer> toChildren(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getChildren(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    // define target as self
    // source as bride
    //this method only used when the person's information only stored in death nodes
    public List<NodePointer> toDeathBride(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getDeathInfo.getBride(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    // define target as self
    // source as groom
    //this method only used when the person's information only stored in death nodes
    public List<NodePointer> toDeathGroom(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getDeathInfo.getGroom(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }
    // define target as self
    // source as sibling
    //this method only used when the person's information only stored in death nodes
    public List<NodePointer> toDeathSibling(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getDeathInfo.getSiblings(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }


}
