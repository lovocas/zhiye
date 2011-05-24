package com.zhiye.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import com.zhiye.dao.QuestionDAO;
import com.zhiye.model.Question;
import com.zhiye.model.User;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 处理：用户的AJaX请求【添加某个问题的答案】 
 * lastUpdatedAt:20:50 2011-05-24
 */
public class AnswerQuestionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        
        
        PrintWriter writer = resp.getWriter();
        //答案的质量应该是前端已经进行验证了
        @SuppressWarnings("deprecation")
        String answerBody = URLDecoder.decode(req.getParameter("answer"));
        String questionId = req.getParameter("qid");
        System.out.println("log:提交的回答->" + answerBody);
        System.out.println(questionId);
        QuestionDAO questionDAO = new QuestionDAO(DB.morphia, DB.mongo);
        Question question = questionDAO.get(new ObjectId(questionId));
        //ask 应该不为空
        System.out.println(question.getTitle());
        User user = (User)req.getSession().getAttribute("user");
        if(null != answerBody && answerBody.length() > 5) {
            user.answer(question, answerBody);
            writer.print("OK");
            return;
        }
        writer.print("FAIL");
        
    }
}
