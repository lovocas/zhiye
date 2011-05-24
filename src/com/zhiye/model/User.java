package com.zhiye.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.zhiye.dao.QuestionDAO;
import com.zhiye.dao.UserDAO;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 *  个人主页的动态信息：
 *  1.关注问题的新信息
 *  2.关注好友的新信息
 *  
 *  关注话题的新信息用于发现自己想回答的新信息
 */
@Entity(value="users")
public class User {

    @Id private ObjectId id;
    private String email;
    private String name;
    private String tagline;
    private String bio;
    private String website;
    private boolean girl;
    private String password;
    private int askCount;
    private int answerCount;


    //提示：
    //关注的问题 话题 人的多对多关系
    //
    private List<ObjectId> followedQuestionIds = new ArrayList<ObjectId>(); //两边都存储
    private List<ObjectId> followedTopicIds  = new ArrayList<ObjectId>();//两边都存储

    private List<ObjectId> followingIds  = new ArrayList<ObjectId>();
    private List<ObjectId> followerIds  = new ArrayList<ObjectId>();

    //回答的问题 User <answer> Question 多对多的关系，同样两边都存储
    private List<ObjectId> answeredQuestionIds  = new ArrayList<ObjectId>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isGirl() {
        return girl;
    }

    public void setGirl(boolean girl) {
        this.girl = girl;
    }

    public int getAskCount() {
        return askCount;
    }

    public void setAskCount(int askCount) {
        this.askCount = askCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public List<ObjectId> getFollowedQuestionIds() {
        return followedQuestionIds;
    }

    public void setFollowedQuestionIds(List<ObjectId> followedQuestionIds) {
        this.followedQuestionIds = followedQuestionIds;
    }

    public List<ObjectId> getFollowedTopicIds() {
        return followedTopicIds;
    }

    public void setFollowedTopicIds(List<ObjectId> followedTopicIds) {
        this.followedTopicIds = followedTopicIds;
    }

    public List<ObjectId> getFollowingIds() {
        return followingIds;
    }

    public void setFollowingIds(List<ObjectId> followingIds) {
        this.followingIds = followingIds;
    }

    public List<ObjectId> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(List<ObjectId> followerIds) {
        this.followerIds = followerIds;
    }

    public List<ObjectId> getAnsweredQuestionIds() {
        return answeredQuestionIds;
    }

    public void setAnsweredQuestionIds(List<ObjectId> answeredQuestionIds) {
        this.answeredQuestionIds = answeredQuestionIds;
    }
    //提出的问题的ID就不保存了， 到时候查询questions//毕竟需求时候少


    public Question ask(String title, String content) {
        Question question = new Question();
        question.setLastModifiedAt(new Date());
        question.setTitle(title);
        question.setBody(content);
        question.setAuthorId(this.getId());
        question.setLastModifiedAt(new Date());
        question.setAuthorId(this.id);
        question.setAuthorName(this.name);
        this.askCount ++;

        QuestionDAO dao = new QuestionDAO(DB.morphia, DB.mongo);
        dao.save(question);
        System.out.println(question.getId() + "问题保存到数据库了，" + this.name + "问的");
        DB.ds.save(this);


        return question;
    }

    public void followPerson(User user) {
        if(!this.equals(user)&&!this.followingIds.contains(user.id) 
                && !user.followerIds.contains(this.id)) {
            this.followingIds.add(user.id);
            user.followerIds.add(this.id);
            UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
            dao.save(this);
            dao.save(user);
        }
    }
    public void unFollowPerson(User user) {
        Datastore ds = DB.ds;
        this.followingIds.remove(user.id);
        user.followerIds.remove(this.id);
        //UserDAO dao = new UserDAO(DB.morphia, DB.mongo);

        //更新自己的列表
        System.out.println(this.id + "&&&&&%&&&&&&&&&&&&&&&&&");
        Query<User> updateQuery = ds.createQuery(User.class).field("_id").equal(this.id);
        UpdateOperations<User> ops = ds.createUpdateOperations(User.class).removeAll("followingIds", user.id);

        //更新user的列表
        Query<User> updateQuery2 = ds.createQuery(User.class).field("_id").equal(user.id);
        UpdateOperations<User> ops2 = ds.createUpdateOperations(User.class).removeAll("followerIds", this.id);
        ds.update(updateQuery, ops);
        ds.update(updateQuery2, ops2);
    }



    public void answer(Question question, String answerBody) {
        Answer answer = new Answer();
        answer.setBody(answerBody);
        answer.setAuthorId(this.id);
        answer.setAuthorName(this.name);

        answer.setQuestionId( question.getId());
        answer.setUpdateAt(new Date());

        this.answerCount ++;
        answer.save(); //
        //回答一个问题默认关注这个问题
        if(!followedQuestionIds.contains(question.getId())) {
            this.followedQuestionIds.add(question.getId());
            this.save();
        }
        question.getAnswers().add(answer);
        question.save();
    }
    public void save() {
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        dao.save(this);
    }


    // 只比较两个user是不是有相同的ObjectId
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || !(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return this.id.equals(otherUser.id);
    }

    //对objectId进行hash, 当然这对存在于数据库中并且已经分配好id的user才有意义
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

}
