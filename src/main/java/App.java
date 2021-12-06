import config.config.ConfigurationManager;
import model.model.Prank;
import model.model.PrankGenerator;
import smtp.SMTPclient;

public class App {

    public static void main(String[] argv){
        ConfigurationManager manager = new ConfigurationManager();
        PrankGenerator generator = new PrankGenerator(manager);
        generator.generatePranks();
    }
}
