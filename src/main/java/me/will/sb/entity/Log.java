package me.will.sb.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * @author 庄佳彬
 * @since 2023/10/14 16:19
 */
public class Log {
    @Id
    private ObjectId id;
    private String title;
    private String method;
    private Object param;
    @Indexed(expireAfterSeconds = 3600 * 24 * 30)
    private Date createTime;
    private Object result;

    public Log() {
        this(null, null);
    }

    public Log(String method, Object param) {
        this.method = method;
        this.param = param;
        this.createTime = new Date();
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
