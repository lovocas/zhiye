package com.zhiye.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.morphia.query.QueryResults;
import com.zhiye.dao.UserDAO;
import com.zhiye.model.User;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 *  lastUpdatedAt:20:51 2011-05-24
 */
public class ViewAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        QueryResults<User> result =  dao.find();
        List<User> users = result.asList();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }

}
