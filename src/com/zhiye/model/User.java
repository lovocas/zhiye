package com.zhiye.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.zhiye.dao.LogDAO;
import com.zhiye.dao.QuestionDAO;
import com.zhiye.dao.TopicDAO;
import com.zhiye.dao.UserDAO;
import com.zhiye.model.Log.Action;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee 
 * 个人主页的动态信息： 1.关注问题的新信息 2.关注好友的新信息
 * 
 * 
 *         关注话题的新信息用于发现自己想回答的新信息
 */
@Entity(value = "users")
public class User implements Idable {
    @Id
    private ObjectId id;
    private String email;
    private String name;
    private String tagline;
    private String bio;
    private String website;
    private boolean girl;
    private String password;
    private int askCount;
    private int answerCount;
    @Reference
    private TimeLine timeline;
    // 提示：
    // 被关注的问题 话题
    private List<ObjectId> followedQuestionIds = new ArrayList<ObjectId>(); // 两边都存储
    private List<ObjectId> followedTopicIds = new ArrayList<ObjectId>();// 两边都存储

    // SNS的follow关系双向都存储，利于查询，保存时同时更新两边
    // 1。不采取@Reference是因为Morphia自动查询出引用的list,这样就会
    // 从数据库中抽取出巨大的冗余数据
    // 2。也可以用Reference lazy=true来解决上面的问题，
    // 衡量下id和DBRef，我觉得还是存储id比较经济
    private List<ObjectId> followingIds = new ArrayList<ObjectId>();
    private List<ObjectId> followerIds = new ArrayList<ObjectId>();

    
    
    
    public TimeLine getTimeline() {
        return timeline;
    }

    public void setTimeline(TimeLine timeline) {
        this.timeline = timeline;
    }

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

    public Question ask(String title, String content) {
        Question question = new Question();
        question.setLastModifiedAt(new Date());
        question.setTitle(title);
        question.setBody(content);
        question.setAuthorId(this.getId());
        question.setLastModifiedAt(new Date());
        question.setCreatedAt(new Date());
        question.setAuthorId(this.id);
        question.setAuthorName(this.name);
        this.askCount++; // 这是错误的用到update来更新

        QuestionDAO dao = new QuestionDAO(DB.morphia, DB.mongo);
        dao.save(question);
        
        
        System.out.println(question.getId() + "问题保存到数据库了，" + this.name + "问的");
        DB.ds.save(this);

        QuestionLog qlog = new QuestionLog();
        qlog.setOwnerId(this.id);
        qlog.setTitle(question.getTitle());
        qlog.setQuestionId(question.getId());
        qlog.setTargetId(question.getId());
        qlog.setAction(Action.NEW);
        LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
        ldao.save(qlog);
        
        return question;
    }

    // 关注某人
    public void followPerson(User user) {
        if (!this.equals(user) && !this.followingIds.contains(user.id)
                && !user.followerIds.contains(this.id)) {
            this.followingIds.add(user.id);
            user.followerIds.add(this.id);
            UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
            dao.save(this);
            dao.save(user);
            
            //log
            
            UserLog ulog = new UserLog();
            ulog.setOwnerId(this.id);
            ulog.setTitle(this.name);
            ulog.setTargetId(user.id);
            ulog.setAction(Action.FOLLOW_USER);
            
            LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
            ldao.save(ulog);
            add2FollowersTimeLine(ulog);
        }
    }

    // 取消关注某人
    public void unFollowPerson(User user) {
        Datastore ds = DB.ds;
        this.followingIds.remove(user.id);
        user.followerIds.remove(this.id);
        // UserDAO dao = new UserDAO(DB.morphia, DB.mongo);

        // 更新自己的列表
        Query<User> updateQuery = ds.createQuery(User.class).field("_id")
                .equal(this.id);
        UpdateOperations<User> ops = ds.createUpdateOperations(User.class)
                .removeAll("followingIds", user.id);

        // 更新user的列表
        Query<User> updateQuery2 = ds.createQuery(User.class).field("_id")
                .equal(user.id);
        UpdateOperations<User> ops2 = ds.createUpdateOperations(User.class)
                .removeAll("followerIds", this.id);
        ds.update(updateQuery, ops);
        ds.update(updateQuery2, ops2);
        
        
        
        //log
        
        UserLog ulog = new UserLog();
        ulog.setOwnerId(this.id);
        ulog.setTitle(this.name);
        ulog.setTargetId(user.id);
        ulog.setAction(Action.UNFOLLOW_USER);
        
        LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
        ldao.save(ulog);
        
    }

    public void answer(Question question, String answerBody) {
        Answer answer = new Answer();
        answer.setBody(answerBody);
        answer.setAuthorId(this.id);
        answer.setAuthorName(this.name);

        answer.setQuestionId(question.getId());
        answer.setUpdateAt(new Date());

        this.answerCount++;
        answer.save(); //

        question.getAnswers().add(answer);
        question.save();
        
        AnswerLog alog = new AnswerLog();
        alog.setOwnerId(this.id);
        alog.setTitle(answer.getBody());
        alog.setAnswerId(answer.getId());
        alog.setTargetId(answer.getId());
        alog.setAction(Action.NEW);
        alog.setTargetParentId(question.getId());
        alog.setTargetParentTitle(question.getTitle());
        
        LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
        ldao.save(alog);
        // 回答一个问题默认关注这个问题
        followQuestion(question);
    }

    public void followTopic(Topic topic) {

        if (!followedTopicIds.contains(topic.getId())) {
            followedTopicIds.add(topic.getId());

            List<ObjectId> tfs = topic.getFollowerIds();
            if (!tfs.contains(this.id)) {
                tfs.add(this.id);
                TopicDAO tdao = new TopicDAO(DB.morphia, DB.mongo);
                tdao.save(topic);
            }
            UserDAO udao = new UserDAO(DB.morphia, DB.mongo);
            udao.save(this);
            
            
            UserLog ulog = new UserLog();
            ulog.setOwnerId(this.id);
            ulog.setTitle(this.name);
            ulog.setTargetId(topic.getId());
            ulog.setAction(Action.FOLLOW_TOPIC);
            
            LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
            ldao.save(ulog);
        }
    }

    public void unfollowTopic(Topic topic) {

        this.followedTopicIds.remove(topic.getId());
        topic.getFollowerIds().remove(this.getId());

        UserDAO udao = new UserDAO(DB.morphia, DB.mongo);
        TopicDAO tdao = new TopicDAO(DB.morphia, DB.mongo);

        Query<User> updateQuery = udao.createQuery().field("_id")
                .equal(this.id);
        UpdateOperations<User> ups = udao.createUpdateOperations().removeAll(
                "followedTopicIds", topic.getId());

        Query<Topic> updateQuery2 = tdao.createQuery().field("_id")
                .equal(topic.getId());
        UpdateOperations<Topic> ups2 = tdao.createUpdateOperations().removeAll(
                "followerIds", this.id);

        udao.update(updateQuery, ups);
        tdao.update(updateQuery2, ups2);
        
        
        UserLog ulog = new UserLog();
        ulog.setOwnerId(this.id);
        ulog.setTitle(this.name);
        ulog.setTargetId(topic.getId());
        ulog.setAction(Action.UNFOLLOW_TOPIC);
        
        LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
        ldao.save(ulog);

    }

    public void followQuestion(Question ques) {
        if (!followedQuestionIds.contains(ques.getId())) {
            followedQuestionIds.add(ques.getId());

            List<ObjectId> qfs = ques.getFollowerIds();
            if (!qfs.contains(this.id)) {
                qfs.add(this.id);
                QuestionDAO qdao = new QuestionDAO(DB.morphia, DB.mongo);
                qdao.save(ques);
            }
            UserDAO udao = new UserDAO(DB.morphia, DB.mongo);
            udao.save(this);
            
            //log
            
            UserLog ulog = new UserLog();
            ulog.setOwnerId(this.id);
            ulog.setTitle(this.name);
            ulog.setTargetId(ques.getId());
            ulog.setAction(Action.FOLLOW_ASK);
            
            LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
            ldao.save(ulog);
            
            add2FollowersTimeLine(ulog);

        }
    }

    public void unfollowQuestion(Question ques) {

        this.followedQuestionIds.remove(ques.getId());
        ques.getFollowerIds().remove(this.id);

        UserDAO udao = new UserDAO(DB.morphia, DB.mongo);
        QuestionDAO qdao = new QuestionDAO(DB.morphia, DB.mongo);

        Query<User> updateQuery = udao.createQuery().field("_id")
                .equal(this.id);
        UpdateOperations<User> ups = udao.createUpdateOperations().removeAll(
                "followedQuestionIds", ques.getId());

        Query<Question> updateQuery2 = qdao.createQuery().field("_id")
                .equal(ques.getId());
        UpdateOperations<Question> ups2 = qdao.createUpdateOperations()
                .removeAll("followerIds", this.id);

        udao.update(updateQuery, ups);
        qdao.update(updateQuery2, ups2);
        
        UserLog ulog = new UserLog();
        ulog.setOwnerId(this.id);
        ulog.setTitle(this.name);
        ulog.setTargetId(ques.getId());
        ulog.setAction(Action.UNFOLLOW_ASK);
        
        LogDAO ldao = new LogDAO(DB.morphia, DB.mongo);
        ldao.save(ulog);

    }

    public void save() {
        UserDAO dao = new UserDAO(DB.morphia, DB.mongo);
        dao.save(this);
    }

    // 只比较两个user是不是有相同的ObjectId
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !(obj instanceof User)) return false;
        User otherUser = (User) obj;
        return this.id.equals(otherUser.id);
    }

    // 对objectId进行hash, 当然这对存在于数据库中并且已经分配好id的user才有意义
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    private void add2FollowersTimeLine(Log log) {
        UserDAO udao = new UserDAO(DB.morphia, DB.mongo);
        List<ObjectId> theFollowerIds = this.followerIds;
        for(ObjectId idd : theFollowerIds) {
            User u = udao.get(idd);
            TimeLine timeline = u.getTimeline();
            if(null == timeline) {
                timeline = new TimeLine();
                timeline.save();
                u.setTimeline(timeline);
                u.save();
            }
            timeline.getLogs().add(log);
            timeline.save();
        }

    }
}
