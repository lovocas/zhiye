package com.zhiye.model;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "zlog")
public abstract class Log {
    @Id
    private ObjectId id;
    private String title;
    private ObjectId targetId;
    private String targetAttr;
    private ObjectId targetParentId;
    private String targetParentTitle;
    private Date happenedAt;
    private ObjectId ownerId;// 一个动作总是人发出的
    private Action action;
    
    public Log() {
        happenedAt = new Date();
    }
    public String getTargetAttr() {
        return targetAttr;
    }

    public void setTargetAttr(String targetAttr) {
        this.targetAttr = targetAttr;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Date getHappenedAt() {
        return happenedAt;
    }

    public void setHappenedAt(Date happenedAt) {
        this.happenedAt = happenedAt;
    }

    public ObjectId getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(ObjectId ownerId) {
        this.ownerId = ownerId;
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

    public ObjectId getTargetId() {
        return targetId;
    }

    public void setTargetId(ObjectId targetId) {
        this.targetId = targetId;
    }

    public ObjectId getTargetParentId() {
        return targetParentId;
    }

    public void setTargetParentId(ObjectId targetParentId) {
        this.targetParentId = targetParentId;
    }

    public String getTargetParentTitle() {
        return targetParentTitle;
    }

    public void setTargetParentTitle(String targetParentTitle) {
        this.targetParentTitle = targetParentTitle;
    }

    public static enum Action {
        FOLLOW_ASK, UNFOLLOW_ASK, FOLLOW_TOPIC, UNFOLLOW_TOPIC, FOLLOW_USER, UNFOLLOW_USER, EDIT, NEW,
    }
}
