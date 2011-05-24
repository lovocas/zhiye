package com.zhiye.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.zhiye.model.Question;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 *lastUpdatedAt:20:50 2011-05-24
 */
public class ViewAllQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        Datastore ds = DB.ds;
        Query<Question> query = ds.createQuery(Question.class);
        List<Question> questions = query.asList();
        req.setAttribute("questions", questions);
        req.getRequestDispatcher("questions.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
