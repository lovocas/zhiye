package com.zhiye.controller;

import java.io.IOException;

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
 * 通过一个ID查找一个USer， 成功的话转发到User页面
 * lastUpdatedAt:20:52 2011-05-24
 */
public class ViewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
            ObjectId id = new ObjectId(req.getParameter("userid"));
            
            UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
            
            User  user = dao.get(id);
            
            if(null != user) {
                req.setAttribute("vieweduser", user);
                System.out.println(user.getName());
                req.getRequestDispatcher("user.jsp").forward(req, resp);
            }
            else {
                //TODO:转向404页面
            }
            

    }

}
