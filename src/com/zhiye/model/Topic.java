package com.zhiye.model;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;

/**
 * @author TeaInCoffee
 * Question 和 Topic 是多对多的关系， 
 * 保存方式为每个Question存放一个Topic列表
 * 而Topic只存放questionCount。
 * 这样考虑是因为会不会通过话题查询问题的频率较低
 * #TODO 需要再思考下
 */
@Entity
public class Topic implements Idable{
    @Id private ObjectId id;
    
    @Indexed(unique=true)
    private String name;
    private String summary;
    
    private int questionCount; 
    private List<ObjectId> followerIds;
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
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public int getQuestionCount() {
        return questionCount;
    }
    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
    public List<ObjectId> getFollowerIds() {
        return followerIds;
    }
    public void setFollowerIds(List<ObjectId> followerIds) {
        this.followerIds = followerIds;
    }
    
}
