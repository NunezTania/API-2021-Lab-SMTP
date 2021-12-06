package model.model;

public class Prank {

    private Person sender;
    private Person witness;
    private Group victim;
    private String message;

    public Prank(Group victim, Person sender, Person witness, String txt){
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

    public Person getWitness() { return witness; }
}
