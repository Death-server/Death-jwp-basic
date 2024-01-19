package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

import static org.reflections.Reflections.log;

@WebServlet("/user/login")
public class UserLogInServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    /**
     * @param req: 클라이언트의 요청, resp: 서버의 응답
     * @todo login.jsp 추가 작성
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(userId);

        if (user!=null){
            if (user.getPassword().equals(password)){
                HttpSession session = req.getSession(); //requestBody로 정보 받아온 것. 쿼리로 하면 주소에 아이디 비번 뜨니까~
                String sessionId = UUID.randomUUID().toString();
                resp.getWriter().write(sessionId);
                Cookie cookie = new Cookie("JSESSIONID",sessionId);
                resp.addCookie(cookie);
                session.setAttribute("user",user);
                resp.sendRedirect("/"); //다들 어디 리다이렉트 했어?
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
