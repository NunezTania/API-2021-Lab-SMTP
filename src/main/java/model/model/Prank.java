package model.model;

public class Prank {

    private Person sender;
    private Person victim;
    private String message;

    public Prank(Person victim, Person sender, String txt){
        this.sender = sender;
        this.victim = victim;
        this.message = txt;
    }

    public Person getVictim() {
        return victim;
    }

    public Person getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

}
