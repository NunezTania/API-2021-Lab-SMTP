import config.config.ConfigurationManager;
import model.model.PrankGenerator;

public class App {

    public static void main(String[] argv){
        ConfigurationManager manager = new ConfigurationManager();
        PrankGenerator generator = new PrankGenerator(manager);
        int nbGroup = Integer.parseInt(manager.getProperties()[2]);
        for(int i = 0; i < nbGroup; i++){
            generator.generatePranks();
        }
    }
}
