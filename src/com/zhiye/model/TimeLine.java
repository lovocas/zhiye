package com.zhiye.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.zhiye.dao.TimeLineDAO;
import com.zhiye.util.DB;

/**
 * @author TeaInCoffee
 * 每个人都有一个timeline
 */
@Entity
public class TimeLine {
    @Id
    private ObjectId id;
    @Reference
    private List<Log> logs = new ArrayList<Log>();
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public List<Log> getLogs() {
        return logs;
    }
    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    public void save() {
        TimeLineDAO dao = new TimeLineDAO(DB.morphia, DB.mongo);
        dao.save(this);
        
    }
    
}
