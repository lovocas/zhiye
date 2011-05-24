package com.zhiye.model;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.zhiye.dao.AnswerDAO;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * DESCRIPTION:
 * 每个答案只能被author修改
 * 保存一个时间，更新时间(updateAt)
 * 
 */
@Entity(value="answers")
public class Answer {
    @Id private ObjectId id;
    
    private String body;
    //belongs to a User: author
    private String authorName;
    private ObjectId AuthorId;
    private Date updateAt;
    //belongs to a question
    private ObjectId questionId;
    // 投票机制
    private int voteCount;
    
    
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public ObjectId getAuthorId() {
        return AuthorId;
    }
    public void setAuthorId(ObjectId authorId) {
        AuthorId = authorId;
    }
    public Date getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    public ObjectId getQuestionId() {
        return questionId;
    }
    public void setQuestionId(ObjectId questionId) {
        this.questionId = questionId;
    }
    
    //这两个就给morphia用， 我们自己修改一个问题就用下面的两个method
    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    
    //认为问题有用就+1，无用就-1
    public void incVoteCount() {
        this.voteCount ++;
    }
    public void decVoteCount() {
        this.voteCount ++;
    }
    
    public void save() {
        AnswerDAO dao = new AnswerDAO(DB.morphia, DB.mongo);
        dao.save(this);
        
    }
    
}
