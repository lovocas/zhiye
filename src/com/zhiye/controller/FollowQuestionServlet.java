package com.zhiye.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.zhiye.dao.QuestionDAO;
import com.zhiye.model.Question;
import com.zhiye.model.User;
import com.zhiye.util.DB;

public class FollowQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        ObjectId questionId = new ObjectId(req.getParameter("qid"));

        QuestionDAO dao = new QuestionDAO(DB.morphia, DB.mongo);
        Question question = dao.get(questionId);

        User user = (User) (req.getSession().getAttribute("user"));

        if (null == user || null == question) {
            out.print("----fail: not a logined user---");
            return;
        }
        user.followQuestion(question);
        out.print("OK");
        
        System.out.println("dddddddddddddd=========");
    }

}
