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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")

public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        User sessionUser = (User)req.getSession().getAttribute("user");
        if(!sessionUser.equals(user)){
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다. ");
        }
        req.setAttribute("user",user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User loggedInUser = (User) value;
            String updatedUserPassword = req.getParameter("updatedUserPassword");
            String updatedUserName = req.getParameter("updatedUserName");
            String updatedUserEmail = req.getParameter("updatedUserEmail");

            User updatedUser = new User(
                    loggedInUser.getUserId(),
                    updatedUserPassword,
                    updatedUserName,
                    updatedUserEmail
            );
            User existingUser = DataBase.findUserById(loggedInUser.getUserId());

            if (existingUser != null) {
                if (existingUser.getUserId().equals(loggedInUser.getUserId())) {
                    DataBase.addUser(updatedUser);
                    log.debug("user : {}", updatedUser);
                    resp.sendRedirect("/user/list");
                } else {
                    log.debug("권한이 없습니다.");
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Permission denied");
                }
            } else {
                log.debug("로그인 되지 않았습니다.");
                resp.sendError(HttpServletResponse.SC_NOT_FOUND,"User not found");
            }
        }else {
            log.debug("로그인 되지 않았습니다. 로그인 화면으로 넘어갑니다.");
            resp.sendRedirect("/user/login.jsp");
        }
    }
}
