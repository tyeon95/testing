package io.muic.ooc.webapp.api.entity;

import io.muic.ooc.webapp.api.entity.auditing.BaseActivityLog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tyeon on 3/7/17.
 */
@Entity
public class ScheduleActivity extends BaseActivityLog {
    @Id
    @GeneratedValue
    private long id;

    //USED TO CHECK ADDS AND REMOVES OF COURSES FROM SCHEDULE

    public long getId() {
        return id;
    }
}
