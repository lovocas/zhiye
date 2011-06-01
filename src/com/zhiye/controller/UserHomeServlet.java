package com.zhiye.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiye.model.Log;
import com.zhiye.model.TimeLine;
import com.zhiye.model.User;

public class UserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        if(null == user) {
            resp.sendRedirect("");
        }
        TimeLine timeline = user.getTimeline();
        List<Log> logs = timeline.getLogs();
        req.setAttribute("logs", logs);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }
}
