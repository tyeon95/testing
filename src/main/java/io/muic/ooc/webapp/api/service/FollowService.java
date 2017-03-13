package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.ActivityLogger;
import io.muic.ooc.webapp.api.ActivityType;
import io.muic.ooc.webapp.api.entity.Follow;
import io.muic.ooc.webapp.api.entity.FollowActivity;
import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.repository.FollowActivityRepository;
import io.muic.ooc.webapp.api.repository.FollowRepository;
import io.muic.ooc.webapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by tyeon on 3/13/17.
 */
@Transactional
@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowActivityRepository followActivityRepository;
    private ActivityLogger activityLogger;
    @PostConstruct
    private void setActivityLogger() {
        activityLogger = new ActivityLogger(FollowActivity.class, followActivityRepository);
    }

    public long count() {
        return followRepository.count();
    }

    public List<Follow> findAll() {
        return followRepository.findAll();
    }

    public Follow findOne(long id) {
        return followRepository.findOne(id);
    }

    private Follow save(Follow follow) {
        return followRepository.save(follow);
    }

    public Follow create(User user) {
        Follow follow = new Follow();
        follow.setUser(user);
        follow = save(follow);
        activityLogger.log(ActivityType.ADDED.toString(), follow.getId(), 0L, null, new Date());
        return follow;
    }

    public Follow follow(long id, Long userId) {
        Follow follow = findOne(id);
        if (follow != null) {
            Set<User> following = follow.getFollowing();
            User user = userRepository.findOne(userId);
            if (user != null) {
                following.add(user);
                follow.setFollowing(following);
                follow = save(follow);
                activityLogger.log(ActivityType.UPDATED.toString(), follow.getId(), userId, "ADD FOLLOW", new Date());
            }
        }
        return follow;
    }

    public Follow unfollow(long id, Long userId) {
        Follow follow = findOne(id);
        if (follow != null) {
            Set<User> following = follow.getFollowing();
            User user = userRepository.findOne(userId);
            if (user != null) {
                following.remove(user);
                follow.setFollowing(following);
                follow = save(follow);
                activityLogger.log(ActivityType.UPDATED.toString(), follow.getId(), userId, "REMOVE FOLLOW", new Date());
            }
        }
        return follow;
    }

    public void archive(long id) {
        Follow follow = findOne(id);
        if (follow != null) {
            follow.setActive(false);
            follow = save(follow);
            activityLogger.log(ActivityType.REMOVED.toString(), follow.getId(), 0L, null, new Date());
        }
    }
}
