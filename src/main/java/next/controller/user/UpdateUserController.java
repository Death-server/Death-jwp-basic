package next.controller.user;

import core.db.DataBase;
import next.controller.Controller;
import next.model.User;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String userId = httpServletRequest.getParameter("userId");
        User user = DataBase.findUserById(userId);
        user.updateUser(httpServletRequest.getParameter("password"), httpServletRequest.getParameter("name"), httpServletRequest.getParameter("email"));
        DataBase.updateUser(user);
        return jspView("redirect:/users");
    }
}
