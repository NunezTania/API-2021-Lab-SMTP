package model.model;

import config.config.ConfigurationManager;
import smtp.SMTPclient;

public class PrankGenerator {

    private ConfigurationManager manager;


    public Prank generatePranks() {
        String[] properties = manager.getProperties();

        // TODO
        // partie 1 : va chercher les info dans les config
        // 1. la liste d'email victime
        // 2. les messages
        Person victim = new Person("magali.egger@heig-vd.ch");
        Person sender = new Person("magali.egger@heig-vd.ch");
        String message = "Hello\n Blabla bla. Voila \n Bye";

        Prank p = new Prank(victim, sender, message);
        // liste de prank a crée et a envoyé a smtp

        return p;
    }

}







