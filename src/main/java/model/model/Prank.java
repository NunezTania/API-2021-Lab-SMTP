package model.model;

public class Prank {

    private final Person sender;
    private final Person witness;
    private final Group victim;
    private final String message;

    public Prank(Group victim, Person sender, Person witness, String txt){
        this.sender = sender;
        this.victim = victim;
        this.message = txt;
        this.witness = witness;
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
