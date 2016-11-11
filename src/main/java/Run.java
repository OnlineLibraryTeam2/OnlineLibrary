import model.Author;
import model.Book;
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

        Client client = new Client("Oleg", "Petrov", 23, "312", "test@mail.ru", "1234");
        Author author = new Author("Fedya", "Vasilyev");
        Book book = new Book("Java", 2010, "Technical", author, 3);
        generalService.addClient(client);
        generalService.addAuthor(author);
        generalService.addBook(book);


        System.out.println(generalService.signIn("test@mail.ru", "1234"));
    }
}
