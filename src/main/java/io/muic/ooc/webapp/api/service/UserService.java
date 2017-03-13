package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.Follow;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.entity.UserGroup;
import io.muic.ooc.webapp.api.repository.FollowRepository;
import io.muic.ooc.webapp.api.repository.ScheduleRepository;
import io.muic.ooc.webapp.api.repository.UserGroupRepository;
import io.muic.ooc.webapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Created by tyeon on 3/6/17.
 */
@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowRepository followRepository;

    public long count() {
        return userRepository.count();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(long id) {
        return userRepository.findOne(id);
    }

//    TODO: for finding friends?
//    public User findByName(String name) {
//        return userRepository.findBy(name);
//    }

//    public Set<User> findByUserGroup(long id) {
//        UserGroup userGroup = userGroupRepository.findOne(id);
//        return userRepository.findByUserGroupOrderByCreatedDesc(userGroup);
//    }

    public UserGroup getUserGroup(long id) {
        User user = findOne(id);
        return user.getUserGroup();
    }

    public Set<Schedule> getSchedules(long id) {
        User user = findOne(id);
        return scheduleRepository.findByUserOrderByIdDesc(user);
    }

    public Follow getFollowing(long id) {
        User user = findOne(id);
        return followRepository.findByUser(user);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    public User create(String studentId, String firstname, String lastname, Long userGroupId, String hashedPassword) {
        User user = new User();
        UserGroup userGroup = null;
        if (userGroupId != null) userGroup = userGroupRepository.findOne(userGroupId);
        if (userGroup != null) {
            user.setStudentId(studentId);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUserGroup(userGroup);
            user.setHashedPassword(hashedPassword);
            //create follow
            Follow follow = followService.create(user);
            user.setFollow(follow);
        }
        return save(user);
    }

    public User update(long id, String studentId, String firstname, String lastname, Long userGroupId, String hashedPassword) {
        User user = findOne(id);
        if (user != null) {
            UserGroup userGroup = (userGroupId != null) ? userGroupRepository.findOne(userGroupId) : null;
            user.setStudentId(studentId);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            if (userGroup != null) user.setUserGroup(userGroup);
            user.setHashedPassword(hashedPassword);
        }
        return user;
    }

    private void archiveRelations(User user) {
        for (Schedule schedule : scheduleRepository.findByUser(user)) {
            schedule.setActive(false);
            scheduleRepository.save(schedule);
        }
    }

    public void archive(long id) {
        User user = findOne(id);
        if (user != null) {
            archiveRelations(user);
            user.setActive(false);
            save(user);
        }
    }

//    public Page<UserGroup> filter(int page, int limit, String code, String name) {
//        PageRequest pageRequest = new PageRequest(page - 1, limit);
//        if (code == null) code = "";
//        if (name == null) name = "";
//        return userGroupRepository.findPaginationByCodeIsNullOrCodeContainingAndNameContainingOrderByCreatedDesc(code, name, pageRequest);
//    }
}
