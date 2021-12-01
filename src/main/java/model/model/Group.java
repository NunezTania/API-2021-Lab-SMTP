package model.model;

import java.util.ArrayList;

public class Group {

    private ArrayList<Person> members = new ArrayList<Person>();

    public ArrayList<Person> getMembers() {
        return members;
    }

    public void addMember(Person p){
        this.members.add(p);
    }

}