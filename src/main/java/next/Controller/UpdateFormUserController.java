package next.Controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormUserController implements Controller{
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        User sessionUser = (User) httpServletRequest.getSession().getAttribute("user");

        httpServletRequest.setAttribute("user", sessionUser);
        return "/user/update.jsp";
    }
}
