package next.mvc;

import next.controller.Controller;
import next.controller.ForwardController;
import next.controller.HomeController;
import next.controller.qna.*;
import next.controller.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private Map<String, Controller> mappings = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    void initMapping() {
        mappings.put("/", new HomeController());
        mappings.put("/users/form", new ForwardController("/user/form.jsp"));
        mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/users", new ListUserController());
        mappings.put("/users/login", new LoginUserFormController());
        mappings.put("/users/profile", new ProfileController());
        mappings.put("/users/logout", new LogoutController());
        mappings.put("/users/create", new CreateUserController());
        mappings.put("/users/updateForm", new UpdateFormUserController());
        mappings.put("/users/update", new UpdateUserController());
        mappings.put("/qnas/show", new ShowController());
        mappings.put("/api/qna/addAnswer", new AddAnswerController());
        mappings.put("/api/qna/deleteAnswer", new RemoveAnswerController());
        mappings.put("/api/qna/addQuestion", new AddQuestionController());
        mappings.put("/api/qna/list", new JsonListController());
        mappings.put("/api/qna/jsonList", new QuestionJsonController());
        mappings.put("/api/qna/updateForm", new UpdateFormQuestionController());
        mappings.put("/api/qna/updateQuestion", new UpdateQuestionController());
        log.debug("Initialize initMapping!");
    }

    public Controller findController(String url) {
        return mappings.get(url);
    }

    void put(String url, Controller controller) {
        mappings.put(url, controller);
    }
}
