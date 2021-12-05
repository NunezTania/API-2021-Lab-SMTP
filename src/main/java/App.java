import config.config.ConfigurationManager;
import model.model.Prank;
import model.model.PrankGenerator;
import smtp.SMTPclient;

public class App {

    public static void main(String[] argv){
        ConfigurationManager manager = new ConfigurationManager();
        PrankGenerator generator = new PrankGenerator();
        Prank prank = generator.generatePranks();

        String[] s = manager.getProperties();
        SMTPclient client = new SMTPclient("127.0.0.1",  Integer.parseInt(s[1]));

    }


}
