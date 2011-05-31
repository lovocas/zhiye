package com.zhiye.model;

import org.bson.types.ObjectId;

public class AnswerLog extends Log {
    //belongs to a answer
    private ObjectId answerId;

    public ObjectId getAnswerId() {
        return answerId;
    }

    public void setAnswerId(ObjectId answerId) {
        this.answerId = answerId;
    }
    

}
