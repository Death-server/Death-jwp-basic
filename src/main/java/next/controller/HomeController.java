package next.controller;

import next.dao.QuestionDao;
import next.view.JspView;
import next.view.ModelAndView;
import next.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller{

    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        httpServletRequest.setAttribute("questions", questionDao.findAll());
        return jspView("/index.jsp");
    }
}
