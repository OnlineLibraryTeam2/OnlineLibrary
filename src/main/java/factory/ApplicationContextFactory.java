package factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexp on 16.10.11.
 */
public class ApplicationContextFactory {
    private static ApplicationContext applicationContext = null;


    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            synchronized (ApplicationContextFactory.class) {
                if(applicationContext == null) {
                    applicationContext = new ClassPathXmlApplicationContext("classpath:/app-context.xml");
                }
            }
        }
        return applicationContext;
    }
}
