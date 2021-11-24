package config.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    public static void main(String[] args) {

        for (String s : new ConfigurationManager().getProperties()) System.out.println(s);

    }

    /* Retourne un tableau de String contenant les valeurs du config.properties */

    public String[] getProperties() {

        String[] propsResult = new String[]{"smtpServerAdress", "smtpServerPort", "numberOfGroups", "witnessesToCC"}; // Champs qu'il y a dans config.properties

        try {

            Properties prop = new Properties();
            String propFileName = "C:\\Users\\FNGUSER004\\IdeaProjects\\API-2021-SMTP\\src\\main\\java\\config\\config\\config.properties";

            InputStream in = new FileInputStream(propFileName);
            prop.load(in);

            for (int i = 0; i < propsResult.length; i++) {

                propsResult[i] = prop.getProperty(propsResult[i]);
            }




        } catch (IOException e) {

            System.err.println(e.getMessage());

        }

        return propsResult;

    }
}
