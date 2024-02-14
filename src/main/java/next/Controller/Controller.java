package next.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    String execute(HttpServletRequest httpServletRequest,
                   HttpServletResponse httpServletResponse) throws Exception;
}
