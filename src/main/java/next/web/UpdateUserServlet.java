package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(value = {"/user/update", "/user/updateForm"})
public class UpdateUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (!sessionUser.equals(user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userId = req.getParameter("userId");

        log.debug("userId = {}", userId);

        User user = DataBase.findUserById(userId);
        user.updateUser(req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));

        resp.sendRedirect("/user/list");
    }
}
