package servlet;

import factory.ApplicationContextFactory;
import model.Client;
import org.springframework.context.ApplicationContext;
import service.GeneralService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by student on 11/11/16.
 */
@WebServlet(urlPatterns = {"/admin"})
public class SearchClientServlet extends HttpServlet {

    public static final String ADMIN_JSP = "/WEB-INF/pages/admin.jsp";

    private ApplicationContext applicationContext;
    private GeneralService generalService;

    @Override
    public void init() throws ServletException {
        applicationContext = ApplicationContextFactory.getInstance();
        generalService = applicationContext.getBean(GeneralService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setAttribute("radio7", generalService.findClientByMail(req.getParameter("mail")));
                req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);

            }
        catch (Exception e) {
            //logger
            e.printStackTrace();
            resp.sendRedirect("/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String name = req.getParameter("name");
        //String surname = req.getParameter("surname");
        String eMail = req.getParameter("mail");
        //String phoneNr = req.getParameter("pnum");

        try {
            if(eMail != null) {
                Client client = generalService.findClientByMail(eMail);
            } else {
                resp.sendRedirect("/index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
