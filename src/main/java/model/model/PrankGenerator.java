package model.model;

import config.config.ConfigurationManager;
import smtp.SMTPclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


public class PrankGenerator {

    private final ConfigurationManager manager;

    public PrankGenerator(ConfigurationManager manager){
        this.manager = manager;
    }

    public void generatePranks() {

        String[] properties = manager.getProperties();
        ArrayList<String> tabEmail = new ArrayList<>(Arrays.asList(manager.getVictims()));

        // recuperation de la taille d'un groupe
        int nbGroup = Integer.parseInt(properties[2]);
        int tailleGroup =  tabEmail.size() / nbGroup;
        if(tailleGroup < 3) tailleGroup = 3;

        // récuperation du numero de port
        int port = Integer.parseInt(properties[1]);


        // récuperation du witness
        String mailWitness = properties[3];

        // création du groupe
        Collections.shuffle(tabEmail);
        Group group = new Group();
        Person sender = new Person(tabEmail.get(0));
        Person witness = new Person(mailWitness);
        for(int i = 1; i < tailleGroup; i++){
            group.addMember(new Person(tabEmail.get(i)));
        }

        // récuperation des messages
        String[] msg = manager.getMessages();

        // crée la prank et l'envoie
        Random rand = new Random();
        Prank p = new Prank(group, sender, witness, msg[rand.nextInt(msg.length)]);
        SMTPclient client = new SMTPclient("127.0.0.1", port);
        client.envoieMail(p);

    }

}







