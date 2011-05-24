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

public class UnFollowUserServlet extends HttpServlet {
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
        System.out.println(user.getName() + "取消关注" + viewedUser.getName());

        user.unFollowPerson(viewedUser);
        
        user = dao.get(user.getId());
        System.out.println("====返回成功 长度变为 " + user.getFollowingIds().size() );
        req.getSession().setAttribute("user", user);
        out.print("OK");
    }
}

