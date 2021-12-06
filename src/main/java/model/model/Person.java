package model.model;

public class Person {

    private String prenom;
    private String email;


    public Person(String email){
        this.email = email;
        this.prenom = email.substring(0, email.lastIndexOf("."));
    }

    public String getPrenom(){
        return prenom;
    }


    public String getEmail() {
        return email;
    }
}
