package io.muic.ooc.webapp.api.entity.auditing;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tyeon on 3/7/17.
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseActivityLog {
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created;

    @Column(name = "activity")
    private String activityName;

    @Column(name = "user")
    private long userId;

    @Column(name = "ref1")
    private long ref1;

    @Column(name = "ref2")
    private long ref2;

    @Column(name = "params")
    private String params;

    @Column(name = "remark")
    private String remark;

    @Column(name = "ts")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRef1() {
        return ref1;
    }

    public void setRef1(long ref1) {
        this.ref1 = ref1;
    }

    public long getRef2() {
        return ref2;
    }

    public void setRef2(long ref2) {
        this.ref2 = ref2;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
