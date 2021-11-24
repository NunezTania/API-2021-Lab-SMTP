package config.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    public static void main(String[] args) {



    }

    /* Retourne un tableau de String contenant les valeurs du config.properties */

    public String[] getProperties() {

        String[] propsResult = new String[]{"smtpServerAdress", "smtpServerPort", "numberOfGroups", "witnessesToCC"}; // Champs qu'il y a dans config.properties
        System.out.println("COUCOU\n");

        try {

            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream in = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (in != null) {
                prop.load(in);
            }

            for (int i = 0; i < propsResult.length; i++) {
                propsResult[i] = new String(prop.getProperty(propsResult[i]));

            }


        } catch (IOException e) {

            System.err.println(e.getMessage());

        }

        return propsResult;

    }
}
