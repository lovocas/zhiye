package com.zhiye.model;

import org.bson.types.ObjectId;

public class QuestionLog extends Log {
    private ObjectId questionId;
    public ObjectId getQuestionId() {
        return questionId;
    }
    public void setQuestionId(ObjectId questionId) {
        this.questionId = questionId;
    }
}
