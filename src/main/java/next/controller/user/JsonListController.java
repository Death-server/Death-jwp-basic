package next.controller.user;

import core.db.DataBase;
import next.controller.Controller;
import next.util.UserSessionUtils;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonListController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        if(!UserSessionUtils.isLogined(httpServletRequest.getSession())) {
            return jsonView().addObject("result", "fail");
        }
        return jsonView().addObject("users", DataBase.findAll());
    }
}
