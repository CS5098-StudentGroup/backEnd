package uk.ac.standrews.cs.Pojo.familyTree;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@NoArgsConstructor
public class NodePointer {
    @Autowired
    GetInfo getInfo;
    String source;
    String target;

    public NodePointer(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public NodePointer toMother(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        System.out.println(target);
        source = getInfo.getMother(valueMap).name;
        return new NodePointer(source,target);
    }

    public NodePointer toFather(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        source = getInfo.getFather(valueMap).name;
        return new NodePointer(source,target);
    }

    public List<NodePointer> toBride(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getBride(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    public List<NodePointer> toGroom(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getGroom(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    public List<NodePointer> toSibling(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getSiblings(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }

    public List<NodePointer> toChildren(Map<String, String> valueMap) throws Exception {
        target = getInfo.getSelf(valueMap).name;
        List<NodePointer> a = new ArrayList<>();
        List<Person> list = getInfo.getChildren(valueMap);
        for (Person person : list) {
            a.add(new NodePointer(person.name, target));
        }
        return a;
    }
}
