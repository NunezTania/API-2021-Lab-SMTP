package model.model;

public class Person {

    private String nom;
    private String prenom;
    private String email;

    public Person(String nom, String prenom, String email){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    // permet de crée une personne a partir de son adresse email
    public Person(String email){
        this.email = email;
        this.prenom = email.substring(0, email.lastIndexOf("."));
        this.nom = email.substring(email.lastIndexOf("."), email.lastIndexOf("@"));
    }

    public String getPrenom(){
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }
}
