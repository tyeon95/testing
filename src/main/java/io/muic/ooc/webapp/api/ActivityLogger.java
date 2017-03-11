package io.muic.ooc.webapp.api;

import io.muic.ooc.webapp.api.entity.auditing.BaseActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by tyeon on 3/11/17.
 */
public class ActivityLogger {

    private Class<? extends BaseActivityLog> activityLogClass;
    private JpaRepository repository;

    public ActivityLogger(Class<? extends BaseActivityLog> activityLogClass, JpaRepository repository) {
        this.activityLogClass = activityLogClass;
        this.repository = repository;
    }

    public void log(String eventName, Long refId1, Long refId2, String params, Date timestamp) {
        BaseActivityLog b = null;
        try {
            b = activityLogClass.newInstance();
            b.setActivityName(eventName);
            b.setRef1(refId1);
            b.setRef2(refId2);
            b.setParams(params);
            b.setTimestamp(timestamp);
            repository.save(b);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void log(String eventName) {
        log(eventName, null, null, null, null);
    }
}