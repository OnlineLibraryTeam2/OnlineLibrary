package servlet;

/**
 * Created by alexp on 16.10.11.
 */

import factory.ApplicationContextFactory;
import model.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.GeneralService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{

    public static final String ADMIN_JSP = "/WEB-INF/pages/admin.jsp";
    public static final String CLIENT_JSP = "/WEB-INF/pages/client.jsp";

    private ApplicationContext applicationContext;
    private GeneralService generalService;

    @Override
    public void init() throws ServletException {
        applicationContext = ApplicationContextFactory.getInstance();
        generalService = applicationContext.getBean(GeneralService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            String inSystem = (String) session.getAttribute("inSystem");
            if (inSystem.equals("admin")) {
                req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
            }else if (inSystem.equals("client")) {
                req.getRequestDispatcher(CLIENT_JSP).forward(req, resp);
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (Exception ex) {
            // logger
            resp.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getParameter("login").equals("admin") && req.getParameter("password").equals("admin")) {

            HttpSession session = req.getSession(true);
            session.setAttribute("inSystem", "admin");

            req.getRequestDispatcher(ADMIN_JSP).forward(req, resp);
        } else if (generalService.signIn(req.getParameter("login"), req.getParameter("password")) != null) {
            Client client = null;

            try {
               client = generalService.findClientByMail(req.getParameter("login"));
           } catch (Exception e){
               resp.sendRedirect("index.jsp");
           }


            HttpSession session = req.getSession(true);
            session.setAttribute("inSystem", "client");
            req.setAttribute("clientName",client.getName());
            req.setAttribute("clientSurname",client.getSurname());

            req.getRequestDispatcher(CLIENT_JSP).forward(req, resp);
        } else {
            req.setAttribute("errorTitle", "LOGIN ERROR");
            req.setAttribute("errorMsg", "You need dto login.\nIf you just tried it and see this message - " +
                    "login or password was wrong");
            //req.getRequestDispatcher("/error").forward(req,resp);

            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }

    }
}
