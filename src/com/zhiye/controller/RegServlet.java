package com.zhiye.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiye.model.User;
import com.zhiye.util.StringUtil;

/**
 * @author TeaInCoffee
 * lastUpdatedAt:20:50 2011-05-24
 */
public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException{
        User user  = null;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String tagLine = req.getParameter("tagline");
        String username = req.getParameter("name");
        String bio = req.getParameter("bio");
        String website = req.getParameter("website");
        String isGirl = req.getParameter("gender");

        user = new User();
        user.setName(StringUtil.iso2UTF(username));//username的唯一性已经验证
        user.setBio(StringUtil.iso2UTF(bio));
        user.setTagline(StringUtil.iso2UTF(tagLine));
        user.setEmail(email); //email的唯一性已经验证
        user.setGirl("1".equals(isGirl)? true:false);
        user.setWebsite(website);
        user.setPassword(password);
        user.save();
        req.getRequestDispatcher("regok.jsp").forward(req, resp);
    }
}
