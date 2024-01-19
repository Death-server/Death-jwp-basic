package next.web;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @brief 서버 측에서 모든 클라이언트의 세션 값을 관리하는 저장 클래스
 */
public class HttpSessionStorage {
    /**
     * @variable 세션을 저장할 맵
     */
    private static final Map<String, HttpSession> sessionMap = new HashMap<>();

    /**
     * @brief 세션을 추가하는 메서드
     */
    public static void addSession(String sessionId, HttpSession session){
        sessionMap.put(sessionId, session);
    }

    public static HttpSession getSession(String sessionId){
        return sessionMap.get(sessionId);
    }

    public static void removeSession(String sessionId){
        sessionMap.remove(sessionId);
    }
}
