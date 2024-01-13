package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.reflections.Reflections.log;

public class UserLogInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); //requestBody로 정보 받아온 것. (Post방식)
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(req.getParameter("userId"));

        if (user!=null){
            //if () 비밀번호 일치체크
            if (user.getPassword().equals(password)){
            session.setAttribute("user",user);
            resp.sendRedirect("/user/list"); //다들 어디 리다이렉트 했어?
            }
            else {
                req.setAttribute("loginFailed",true);
                RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
                rd.forward(req, resp);
            }

        }
        else {
            req.setAttribute("loginFailed",true);
            RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
            rd.forward(req, resp);
        }


    }


}
