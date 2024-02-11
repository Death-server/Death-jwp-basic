package next.Controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String userId = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("password");

        User user = DataBase.findUserById(userId);

        if(user == null) {
            return "redirect:/user/login_failed.jsp";
        }
        if(!user.getPassword().equals(password)) {
            return "redirect:/user/logins_failed.jsp";
        }

        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user", user);
        return "redirect:/";
    }
}
