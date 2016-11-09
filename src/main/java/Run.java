import dao.interfaces.AuthorDao;
import model.Author;
import model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.GeneralService;
import spring_config.SpringConfig;

/**
 * Created by student on 11/9/16.
 */
public class Run {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        GeneralService service = context.getBean(GeneralService.class);
        Author author = new Author("Vasya", "Pupkin");
        Book book = new Book("java", 1998, "tech", author, 3);
        service.addAuthor(author);
        service.addBook(book);
        service.deleteAuthor(author);
    }
}
