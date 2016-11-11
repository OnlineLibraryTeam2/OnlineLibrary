import model.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.GeneralService;

/**
 * Created by alexp on 16.10.11.
 */
public class Run {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-context.xml");
        GeneralService generalService = applicationContext.getBean(GeneralService.class);

        Client client = new Client("Oleg", "ddd", 23, "312", "test@mail.ru", "1234");
        generalService.addClient(client);
    }
}
