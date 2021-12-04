package config.config;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ConfigurationManager {


    public String[] getProperties() {
        String[] propsResult = new String[]{"smtpServerAdress", "smtpServerPort", "numberOfGroups", "witnessesToCC"}; // Champs qu'il y a dans config.properties

        try {
            Properties prop = new Properties();
            String propFileName = "C:\\Users\\magal\\Documents\\POO\\API-2021-Lab-SMTP\\src\\main\\java\\config<\\config\\config.properties";
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

    public ArrayList<String> getVictims(){
        try {
            FileInputStream fi = new FileInputStream(".\\config\\config\\victims.utf8");
            BufferedReader in = new BufferedReader(new InputStreamReader(fi));
            ArrayList<String> victims = new ArrayList<>();
            while (in.readLine() != -1){
                victims.add(in.);
            }
        }
    }
}
