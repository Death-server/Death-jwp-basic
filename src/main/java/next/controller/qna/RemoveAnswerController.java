package next.controller.qna;

import next.controller.Controller;
import next.dao.AnswerDao;
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
        Result result = answerDao.remove(Long.parseLong(req.getParameter("answerId").trim()));
        log.error(req.getParameter("answerId"));
        log.error(result.toString());

        return jsonView();
    }
}
