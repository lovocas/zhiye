package com.zhiye.model;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


@Entity(value="zlog")
public abstract class Log {
    @Id private ObjectId id;
    String title;
    ObjectId targetId;
    ObjectId targetParentId;
    String targetParentTitle;
    
    ObjectId ownerId;//一个动作总是人发出的
    private ULAction action;
    
    public ULAction getAction() {
        return action;
    }
    public void setAction(ULAction action) {
        this.action = action;
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
    
    
    public enum ULAction {
        FOLLOW_ASK,
        UNFOLLOW_ASK,
        FOLLOW_TOPIC,
        UNFOLLOW_TOPIC,
        FOLLOW_USER,
        UNFOLLOW_USER,
        EDIT,
        NEW,
    }
    
    public static void main(String[] args) {
        System.out.println(ULAction.FOLLOW_ASK);
    }
}
