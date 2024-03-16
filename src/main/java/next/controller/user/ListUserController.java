package next.controller.user;

import core.db.DataBase;
import next.controller.Controller;
import next.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        if(!UserSessionUtils.isLogined(httpServletRequest.getSession())) {
            return "redirect:/users/loginForm";
        }
        httpServletRequest.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
