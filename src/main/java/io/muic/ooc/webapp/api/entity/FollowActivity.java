package io.muic.ooc.webapp.api.entity;

import io.muic.ooc.webapp.api.entity.auditing.BaseActivityLog;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tyeon on 3/13/17.
 */
@Entity
public class FollowActivity extends BaseActivityLog {
    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }
}
