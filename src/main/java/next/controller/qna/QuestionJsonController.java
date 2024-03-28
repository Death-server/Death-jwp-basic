package next.controller.qna;

import next.controller.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.view.JsonView;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionJsonController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Long questionId = Long.parseLong(httpServletRequest.getParameter("questionId"));
        AnswerDao answerDao = new AnswerDao();
        ModelAndView modelAndView = new ModelAndView(new JsonView());
        List<Answer> answers =  answerDao.findAllByQuestionId(questionId);
        return modelAndView.addObject("answers", answers);
    }
}
