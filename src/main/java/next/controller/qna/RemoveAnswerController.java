package next.controller.qna;

import next.controller.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Result;
import next.view.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveAnswerController implements Controller {

    Logger log = LoggerFactory.getLogger(RemoveAnswerController.class);

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        AnswerDao answerDao = new AnswerDao();
        QuestionDao questionDao = new QuestionDao();
        Long answerId = Long.parseLong(req.getParameter("answerId").trim());

        Answer answer = answerDao.findById(answerId);
        questionDao.update(answer.getQuestionId(), false);
        Result result = answerDao.remove(answerId);
        log.error(answerId.toString());
        log.error(result.toString());

        return jsonView().addObject("status", true);
    }
}
