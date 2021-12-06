package model.model;

import config.config.ConfigurationManager;
import smtp.SMTPclient;

import java.util.ArrayList;

public class PrankGenerator {

    private ConfigurationManager manager;

    public PrankGenerator(ConfigurationManager manager){
        this.manager = manager;
    }

    public Prank generatePranks() {
        String[] properties = manager.getProperties();
        int nbGroup = Integer.parseInt(properties[2]);
        String[] tabEmail = manager.getVictims();
        int tailleGroup =  tabEmail.length / nbGroup ;

        Group group = new Group();
        Person sender = new Person(tabEmail[0]);

        for(int i = 1; i < tailleGroup; i++){
            group.addMember(new Person(tabEmail[i]));
        }

        String[] msg = manager.getMessages();



        Prank p = new Prank(group, sender, msg[0]);
        // liste de prank a crée et a envoyé a smtp

        return p;
    }

}







