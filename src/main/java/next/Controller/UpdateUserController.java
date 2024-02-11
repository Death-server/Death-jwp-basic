package next.Controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller{

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String userId = httpServletRequest.getParameter("userId");

        User user = DataBase.findUserById(userId);
        user.updateUser(httpServletRequest.getParameter("password"), httpServletRequest.getParameter("name"), httpServletRequest.getParameter("email"));

        return "redirect:/users";
    }
}
