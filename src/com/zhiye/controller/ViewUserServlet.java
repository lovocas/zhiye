package com.zhiye.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.google.code.morphia.query.Query;
import com.zhiye.dao.LogDAO;
import com.zhiye.dao.UserDAO;
import com.zhiye.model.Log;
import com.zhiye.model.User;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 通过一个ID查找一个USer， 成功的话转发到User页面
 * lastUpdatedAt:20:52 2011-05-24
 * 获得ownerId为该人的所有log
 */
public class ViewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
            ObjectId id = new ObjectId(req.getParameter("userid"));
            
            UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
            
            User  user = dao.get(id);
            if(null != user) {
                LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
                Query<Log> query = ldao.createQuery().field("ownerId").equal(user.getId()).order("-happenedAt");
                List<Log> logs = query.asList();
                for(Log l : logs) {
                    System.out.println(l.getTitle());
                    System.out.println(l.getTargetId());
                }
                req.setAttribute("logs", logs);
                req.setAttribute("vieweduser", user);
                System.out.println(user.getName());
                req.getRequestDispatcher("user.jsp").forward(req, resp);
                
            }
            else {
                //TODO:转向404页面
            }
            

    }
    
    

}
