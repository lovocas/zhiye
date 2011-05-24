package com.zhiye.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.zhiye.model.User;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 处理用户登录
 * lastUpdatedAt:20:50 2011-05-24
 */
public class LoginServlet extends HttpServlet {

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
            throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Datastore ds = DB.ds;
        if(password != null && null!=email && "" != email) {
            Query<User> query = ds.createQuery(User.class).field("email").equal(email);
            List<User> list = query.asList();
            if(list.size() > 0 ) {
                User u = list.get(0);
                if(password.equals(u.getPassword())) {
                    req.getSession().setAttribute("user", u);
                    System.out.println(u.getName() + "========");
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
