package com.zhiye.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.zhiye.dao.QuestionDAO;
import com.zhiye.model.Log.Action;
import com.zhiye.util.DB;


/**
 * @author TeaInCoffee
 * modified Ask 
 * clarify some basic relation
 * 1. relation to author/modifier
 * 2. relation to answers
 * 
 * 3 TODO:relation to followers
 */
@Entity(value="questions")
public class Question implements Idable {
    @Id private ObjectId id;
    
    //BASIC INFORMATION
    private String title;
    private String body;    
    //shortcut of basic inf
    //if need more information use this fKey to User collection
    private String authorName;
    private ObjectId authorId; //belongs to user

    private Date createdAt;
    
    //最近修改这个问题的人
    private String lastModUserName;
    private ObjectId lastModUserId;
    
    private Date lastModifiedAt;
    
    //have all his answers in hand, 
    //avoid doing more query via retrieve all answers and get test each qustionid;
    //自动得到属于它的所有answer,answer里面本身并不会有更多的reference，所以不会load很大一张图
    //Followers
    private List<ObjectId> followerIds = new ArrayList<ObjectId>();
    
    @Reference(lazy=true)
    private List<Topic> topics = new ArrayList<Topic>();
    
    //这个Refercence自动找出所有的与之对应的答案，
    //因为常常查看某个问题的页面，并且罗列出所有回答
    //但是lazy=true必须用，因为其他情况可能需要很多Question的基本信息而不需要他的answert
    @Reference(lazy=true)
    private List<Answer> answers = new ArrayList<Answer>();
    
    public Question() {
        
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastModUserName() {
        return lastModUserName;
    }

    public void setLastModUserName(String lastModUserName) {
        this.lastModUserName = lastModUserName;
    }

    public ObjectId getLastModUserId() {
        return lastModUserId;
    }

    public void setLastModUserId(ObjectId lastModUserId) {
        this.lastModUserId = lastModUserId;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<ObjectId> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(List<ObjectId> followerIds) {
        this.followerIds = followerIds;
    }
    
    
    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void save() {
        QuestionDAO dao = new QuestionDAO(DB.morphia, DB.mongo);
        dao.save(this);
    }
    //setter and getter

    //TODO:Follower
    private void insertActionLog(Action action) {
        QuestionLog qlog = new QuestionLog();
        qlog.setTitle(this.title);
        qlog.setTargetId(this.id);
        qlog.setAction(action);
        
        if(action == Action.EDIT) {
        }
    }
}
