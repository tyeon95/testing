package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.entity.UserGroup;
import io.muic.ooc.webapp.api.repository.UserGroupRepository;
import io.muic.ooc.webapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by tyeon on 3/7/17.
 */

@Transactional
@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private UserRepository userRepository;

    public long count() {
        return userGroupRepository.count();
    }

    public List<UserGroup> findAll() {
        return userGroupRepository.findAll();
    }

    public UserGroup findOne(long id) {
        return userGroupRepository.findOne(id);
    }

    private UserGroup save(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }

    public UserGroup create(String role, String description) {
        UserGroup userGroup = new UserGroup();
        userGroup.setRole(UserGroup.RoleType.valueOf(role));
        userGroup.setDescription(description);
        return save(userGroup);
    }

    public UserGroup update(long id, String role, String description) {
        UserGroup userGroup = findOne(id);
        if (userGroup != null) {
            userGroup.setRole(UserGroup.RoleType.valueOf(role));
            userGroup.setDescription(description);
            userGroup = save(userGroup);
        }
        return userGroup;
    }

    private void nullifyRelations(UserGroup userGroup) {
        for (User user : userRepository.findByUserGroup(userGroup)) {
            user.setUserGroup(null);
            userRepository.save(user);
        }
    }

    public void archive(long id) {
        UserGroup userGroup = findOne(id);
        if (userGroup != null) {
            nullifyRelations(userGroup);
            userGroup.setActive(false);
            save(userGroup);
        }
    }
}