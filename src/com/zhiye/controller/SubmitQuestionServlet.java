package com.zhiye.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiye.model.Question;
import com.zhiye.model.User;
import com.zhiye.util.StringUtil;

/**
 * @author TeaInCoffee
 * lastUpdatedAt:20:50 2011-05-24
 */
public class SubmitQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        process(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        System.out.println("dadaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa===111+");
        //title和content的验证放在客户端
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        User u = (User)(req.getSession().getAttribute("user"));
        
        //检查身份
        if(null == u) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        if(null != title &&title.length() > 10) {
            Question question = u.ask(StringUtil.iso2UTF(title), StringUtil.iso2UTF(content));
            req.setAttribute("question", question);
            req.getRequestDispatcher("question.jsp").forward(req, resp);
        }
    }
}
