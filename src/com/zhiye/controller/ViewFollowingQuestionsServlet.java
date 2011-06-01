package com.zhiye.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.google.code.morphia.query.Query;
import com.zhiye.dao.QuestionDAO;
import com.zhiye.model.Question;
import com.zhiye.model.User;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 已经登陆的用户， 查看所有自己关注的问题
 */
public class ViewFollowingQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(null == user) {
            resp.sendRedirect("");
            return;
        }
        
        List<ObjectId> qids = user.getFollowedQuestionIds();
        if(qids.size() <= 0) {
            resp.sendRedirect("");
            return;
        }
        QuestionDAO qdao = new QuestionDAO(DB.morphia, DB.mongo);
        Query<Question> query = qdao.createQuery().field("_id").hasAnyOf(qids).order("-createdAt");
        List<Question> questions = query.asList();
        req.setAttribute("questions", questions);
        req.setAttribute("title", "我关注的问题");
        req.getRequestDispatcher("questions.jsp").forward(req, resp);
        
    }
    
    
    
    
}
