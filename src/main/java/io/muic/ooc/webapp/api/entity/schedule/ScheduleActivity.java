package io.muic.ooc.webapp.api.entity.schedule;

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

    public long getId() {
        return id;
    }
}
