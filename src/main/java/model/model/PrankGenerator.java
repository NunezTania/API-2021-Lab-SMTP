package model.model;

import config.config.ConfigurationManager;
import smtp.SMTPclient;


public class PrankGenerator {

    private final ConfigurationManager manager;

    public PrankGenerator(ConfigurationManager manager){
        this.manager = manager;
    }

    public void generatePranks() {

        String[] properties = manager.getProperties();

        // initialisation de la taille des groupes
        int nbGroup = Integer.parseInt(properties[2]);
        String[] tabEmail = manager.getVictims();
        int tailleGroup =  tabEmail.length / nbGroup;
        if(tailleGroup < 1) tailleGroup = 1;

        // récuperation du numero de port
        int port = Integer.parseInt(properties[1]);

        // récuperation des messages
        String[] msg = manager.getMessages();

        // récuperation du witness
        String mailWitness = properties[3];

        // création du groupe
        Group group = new Group();
        Person sender = new Person(tabEmail[0]);
        Person witness = new Person(mailWitness);
        for(int i = 1; i < tailleGroup; i++){
            group.addMember(new Person(tabEmail[i]));
        }

        // crée la prank et l'envoie
        Prank p = new Prank(group, sender, witness, msg[0]);
        SMTPclient client = new SMTPclient("127.0.0.1", port);
        client.envoieMail(p);

    }

}







