package next.controller.qna;

import next.controller.Controller;
import next.dao.QuestionDao;
import next.model.Question;
import next.util.UserSessionUtils;
import next.view.JspView;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuestionController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        if(!UserSessionUtils.isLogined(httpServletRequest.getSession())) {
            return new ModelAndView(new JspView("redirect:/user/login_failed.jsp"));
        }
        QuestionDao questionDao = new QuestionDao();
        Question question = new Question(httpServletRequest.getParameter("writer"),
                httpServletRequest.getParameter("title"),
                httpServletRequest.getParameter("contents"));

        Long questionId = Long.parseLong(httpServletRequest.getParameter("questionId").trim());
        questionDao.update(questionId, question);

        return new ModelAndView(new JspView("redirect:/"));
    }
}
