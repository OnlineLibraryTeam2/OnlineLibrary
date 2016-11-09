import dao.interfaces.AuthorDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.GeneralService;
import spring_config.SpringConfig;

/**
 * Created by student on 11/9/16.
 */
public class Run {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        GeneralService service = context.getBean(GeneralService.class);
        //service.addAuthor("Vasya", "Pupkin");
    }
}
