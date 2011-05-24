package com.zhiye.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.zhiye.dao.UserDAO;
import com.zhiye.model.User;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 对用户点击FOLLOW别人的按钮的时候接收AJAX请求并且做出响应
 * lastUpdatedAt:20:50 2011-05-24
 */
public class FollowUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        
        
        ObjectId viewedUserId = new ObjectId(req.getParameter("viewedid"));
       
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        User viewedUser = dao.get(viewedUserId);
        
        
        User user = (User)(req.getSession().getAttribute("user"));
        
        if(null == user) {
            out.print("----fail: not a logined user---");
            return;
        }
        user.followPerson(viewedUser);
        out.print("ok");
    }
}
