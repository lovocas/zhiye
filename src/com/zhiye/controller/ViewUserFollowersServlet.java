package com.zhiye.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * 02:05 2011-05-25
 * 查看一个user的所有followers
 * input: 这个user的id
 * output:这个user的所有followers列表
 * 
 */
public class ViewUserFollowersServlet extends HttpServlet {
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
    throws ServletException, IOException {
        String viewedId = req.getParameter("viewedid");

        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        User viewedUser = dao.get(new ObjectId(viewedId));
        List<ObjectId> followerIds = viewedUser.getFollowerIds();
        List<User> followers = new ArrayList<User>();

        for(ObjectId id: followerIds) {
            User u = dao.get(id);
            followers.add(u);
        }
        System.out.println(followers.size() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        req.setAttribute("followers", followers);
        req.getRequestDispatcher("user-followers.jsp").forward(req, resp);
    }
}
