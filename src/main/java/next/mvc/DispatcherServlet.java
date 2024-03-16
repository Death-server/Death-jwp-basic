package next.mvc;


import next.controller.Controller;
import next.controller.qna.AddAnswerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = {"/", ""}, loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private  static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
    private static final long serialVersionUId = 1L;
    private final String REDIRECT_PREFIX = "redirect:";

    private RequestMapping requestMapping;

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
        requestMapping.initMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();
        log.error("URL : {}", req.getRequestURI());

        Controller controller = requestMapping.findController(requestUri);

        try {
            String viewName = controller.execute(req, resp);
            if (viewName != null) {
                move(viewName, req, resp);
            }
            log.debug("controller : {}", controller);
        } catch(Throwable e) {
            throw  new ServletException(e.getMessage());
        }
    }

    private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(viewName.startsWith(REDIRECT_PREFIX)) {
            resp.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}
