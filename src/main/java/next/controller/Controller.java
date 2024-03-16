package next.controller;

import next.view.JsonView;
import next.view.JspView;
import next.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    ModelAndView execute(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws Exception;
    
    default ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
    
    default ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }
}
