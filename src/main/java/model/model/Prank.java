package model.model;

public class Prank {

    private Person sender;
    private Group victim;
    private String message;

    public Prank(Group victim, Person sender, String txt){
        this.sender = sender;
        this.victim = victim;
        this.message = txt;
    }

    public Group getVictim() {
        return victim;
    }

    public Person getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

}
