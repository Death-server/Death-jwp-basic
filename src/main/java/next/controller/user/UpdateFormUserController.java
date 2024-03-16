package next.controller.user;

import next.controller.Controller;
import next.model.User;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        User sessionUser = (User) httpServletRequest.getSession().getAttribute("user");

        httpServletRequest.setAttribute("user", sessionUser);
        return jspView("/user/update.jsp");
    }
}
