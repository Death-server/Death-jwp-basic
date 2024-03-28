package next.controller.qna;

import next.controller.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.view.ModelAndView;
import next.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        System.out.println("sss : " + questionDao.findById(questionId).getTitle());
        ModelAndView view = jspView("/qna/show.jsp");
        view.addObject("question", questionDao.findById(questionId));
        view.addObject("answers", answerDao.findAllByQuestionId(questionId));
        return view;
    }
}
