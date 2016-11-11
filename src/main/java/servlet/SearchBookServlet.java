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

/**
 * Created by alexp on 16.11.11.
 */
@WebServlet(urlPatterns = {"/client"})
public class SearchBookServlet extends HttpServlet {

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

        try {
                    req.setAttribute("books",generalService.searchBookTitle(req.getParameter("title")));
                    req.getRequestDispatcher(CLIENT_JSP).forward(req, resp);
        } catch (Exception ex) {
            // logger
            resp.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("books",generalService.searchBookTitle(req.getParameter("title")));
        req.getRequestDispatcher(CLIENT_JSP).forward(req, resp);

    }
}
