package next.controller.user;

import core.db.DataBase;
import next.controller.Controller;
import next.model.User;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserFormController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String userId = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("password");

        User user = DataBase.findUserById(userId);

        if(user == null) {
            return jspView("redirect:/user/login_failed.jsp");
        }
        if(!user.getPassword().equals(password)) {
            return jspView("redirect:/user/logins_failed.jsp");
        }
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", user);

        return jspView("redirect:/").addObject("user", user);
    }
}
