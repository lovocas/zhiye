package com.zhiye.dao;

import org.bson.types.ObjectId;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import com.zhiye.model.Answer;
import com.zhiye.util.MongoDB;

public class AnswerDAO extends BasicDAO<Answer, ObjectId>{
    public AnswerDAO( Morphia morphia, Mongo mongo ) {
        super(mongo, morphia, MongoDB.DB_NAME);
    }
}
