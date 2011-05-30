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
 * 00:28 2011-05-31
 * 查询某个id的user的全部 following
 * #TODO:修改下面的查询为一次查询 改为in []
 */
public class ViewUserFollowingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String viewedId = req.getParameter("viewedid");
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        User viewedUser = dao.get(new ObjectId(viewedId));
        List<ObjectId> followingIds = viewedUser.getFollowingIds();
        List<User> followings = new ArrayList<User>();

        for(ObjectId id: followingIds) {
            User u = dao.get(id);
            followings.add(u);
        }
        System.out.println(followings.size() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        req.setAttribute("followings", followings);
        req.setAttribute("vieweduser", viewedUser);
        req.getRequestDispatcher("user-followings.jsp").forward(req, resp);
        
        
    }
}
