package com.zhiye.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.zhiye.dao.QuestionDAO;
import com.zhiye.model.Question;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 通过ID 查找对应的问题。
 * 成功的话专项问题页面：question.jsp
 * lastUpdatedAt:20:51 2011-05-24
 */
public class ViewQuestionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

        process(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }
    /**
     * 可能通过两种方式请求这个servlet：
     * 1. request 转发， 用req做参数
     * 2. 直接客户端请求， 用param做参数
     */
    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        ObjectId id = (ObjectId)req.getAttribute("qid");
        if(null == id) {
            String qsid = req.getParameter("qid");
            if(null == qsid) {
                //TODO:定向到404页，找不到这个
                return;
            }
            id = new ObjectId(qsid);
        }
        
        QuestionDAO dao = new QuestionDAO(DB.morphia, DB.mongo);

        Question question = dao.get(id);
        if(null == question) {
            System.out.println("找不到id所对应的question");
            //TODO: 定向到404
            return;
        }
        
        //通过ID找到对应的问题
        req.setAttribute("question", question);
        req.getRequestDispatcher("question.jsp").forward(req, resp);
    }
}
