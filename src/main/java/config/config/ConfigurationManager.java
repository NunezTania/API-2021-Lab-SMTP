package config.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Class ConfigurationManager
 * Permet de récupérer les paramètres de configuration dans les fichiers config.properties, messages.utf8 et
 * victims.utf8 sous forme d'Array de String. Cette classe fournit trois fonction gérant ces fonctionnalités.
 */

public class ConfigurationManager {

    public static void main(String[] args) {

    }

    /**
     * Cette fonction récupère les paramètres de configuration
     *  @return un tableau de String contenant les valeurs du config.properties
     */

    public String[] getProperties() {

        String[] propsResult = new String[]{"smtpServerAdress", "smtpServerPort", "numberOfGroups", "witnessesToCC"}; // Champs qu'il y a dans config.properties

            for (int i = 0; i < propsResult.length; i++) {

                propsResult[i] = getProperty(propsResult[i]);
            }

        return propsResult;

    }

    /**
     * Cette fonction récupère les paramètres de configuration
     * @param s la propriété désirée
     *  @return une String contenant la valeur désirée dans le fichier config.properties
     */

    public String getProperty(String s) { // Champs qu'il y a dans config.properties

        try {

            Properties prop = new Properties();
            String propFileName = System.getProperty("user.dir") + "\\src\\main\\java\\config\\config\\config.properties";

            //String propFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";

            InputStream in = new FileInputStream(propFileName);
            prop.load(in);

            return prop.getProperty(s);




        } catch (IOException e) {

            System.err.println(e.getMessage());

        }
        return null;
    }

    /**
     * Cette fonction récupère les victimes à qui faire la Prank
     *  @return un tableau de String contenant les emails des victimes
     */

    public String[] getVictims() {
        try {
            //String victimsFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";
            String victimsFileName = System.getProperty("user.dir") + "\\src\\main\\java\\config\\config\\victims.utf8";
            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(victimsFileName), StandardCharsets.UTF_8));
            String line;
            List<String> lines = new ArrayList<>();

            while ((line = buff.readLine()) != null) {
                lines.add(line);
            }
            String[] ret = new String[0];
            return lines.toArray(ret);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Cette fonction récupère les messages à envoyer aux victimes receptionnistes
     *  @return un tableau de String contenant les messages à envoyer (Sujet et corps de message).
     */

    public String[] getMessages() {
        try {
            //String messagesFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";
            String messagesFileName = System.getProperty("user.dir") + "\\src\\main\\java\\config\\config\\messages.utf8";
            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(messagesFileName), StandardCharsets.UTF_8));
            String line;
            List<String> lines = new ArrayList<>();
            StringBuilder s = new StringBuilder();

            while ((line = buff.readLine()) != null) {

                s.append(line);
                s.append("\n");

                if (line.matches("--")) {
                    lines.add(s.toString().replaceAll("--", ""));
                    s = new StringBuilder();
                }

            }
            String[] ret = new String[0];
            return lines.toArray(ret);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
