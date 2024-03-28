package next.controller.qna;

import next.controller.Controller;
import next.dao.QuestionDao;
import next.model.Question;
import next.view.JsonView;
import next.view.JspView;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormQuestionController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findById(Long.parseLong(httpServletRequest.getParameter("questionId").trim()));


        ModelAndView modelAndView = new ModelAndView(new JspView("/qna/update.jsp"));
        modelAndView.addObject("question", question);

        return modelAndView;
    }
}
