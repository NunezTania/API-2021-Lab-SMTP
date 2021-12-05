package config.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConfigurationManager {
    public static void main(String[] args) {

        for (String s : new ConfigurationManager().getMessages()) {
            System.out.println("\n");
            System.out.println(s);
        }

    }

    /* Retourne un tableau de String contenant les valeurs du config.properties */

    public String[] getProperties() {

        String[] propsResult = new String[]{"smtpServerAdress", "smtpServerPort", "numberOfGroups", "witnessesToCC"}; // Champs qu'il y a dans config.properties

            for (int i = 0; i < propsResult.length; i++) {

                propsResult[i] = getProperty(propsResult[i]);
            }

        return propsResult;

    }

    public String getProperty(String s) { // Champs qu'il y a dans config.properties

        try {

            Properties prop = new Properties();
            // String absolutePath = System.getProperty("user.dir");
            String propFileName = "C:\\Users\\FNGUSER004\\IdeaProjects\\API-2021-SMTP\\src\\main\\java\\config\\config\\config.properties";
            //String propFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";

            InputStream in = new FileInputStream(propFileName);
            prop.load(in);

            return prop.getProperty(s);




        } catch (IOException e) {

            System.err.println(e.getMessage());

        }
        return null;
    }

    public String[] getVictims() {
        try {
            //String victimsFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";
            String victimsFileName = "C:\\Users\\FNGUSER004\\IdeaProjects\\API-2021-SMTP\\src\\main\\java\\config\\config\\victims.utf8";
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

    public String[] getMessages() {
        try {
            //String messagesFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";
            String messagesFileName = "C:\\Users\\FNGUSER004\\IdeaProjects\\API-2021-SMTP\\src\\main\\java\\config\\config\\messages.utf8";
            BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(messagesFileName), StandardCharsets.UTF_8));
            String line;
            List<String> lines = new ArrayList<>();
            StringBuilder s = new StringBuilder();

            while ((line = buff.readLine()) != null) {

                s.append(line);
                s.append("\n");

                if (line.matches("--")) {
                    lines.add(s.toString());
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
