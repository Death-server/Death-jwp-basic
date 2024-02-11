package next.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    String execute(HttpServletRequest httpServletRequest,
                   HttpServletResponse httpServletResponse) throws Exception;
}
