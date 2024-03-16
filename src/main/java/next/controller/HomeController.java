package next.controller;

import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller{

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        httpServletRequest.setAttribute("questions", questionDao.findAll());
        return "/index.jsp";
    }
}
