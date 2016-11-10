package filter;

import org.apache.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alexp on 16.10.11.
 */
public class SessionFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("created SessionFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)){
            //redirect
            return;
        } else {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            LOG.info("SessionFilter: " + request.getRequestURI() );

            HttpSession session = request.getSession();
            try {
                boolean inSystem = (boolean) session.getAttribute("inSystem");
                if (!inSystem) {
                    throw new Exception();
                }
                LOG.info("SessionFilter: Session is alive");
            } catch (Exception ex) {
                ex.printStackTrace();
                request.setAttribute("errorTitle", "ERROR");
                request.setAttribute("errorMsg", "You need dto login.\nIf you just tried but see this message - " +
                        "login or password was wrong");
                LOG.info("SessionFilter: Session is dead");
                request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.info("SessionFilter destroyed");
    }
}
