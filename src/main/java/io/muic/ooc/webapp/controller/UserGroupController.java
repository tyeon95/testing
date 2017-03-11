package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.entity.UserGroup;
import io.muic.ooc.webapp.api.service.UserGroupService;
import io.muic.ooc.webapp.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyeon on 3/10/17.
 */
@RestController
@RequestMapping(value = "/"+UserGroup.PLURAL)
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Map getUserGroups() {
        HashMap<String, Object> frb = new HashMap<>();
        List<UserGroup> userGroups = userGroupService.findAll();
        frb.put(UserGroup.PLURAL, userGroups);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.GET)
    public Map getUserGroup(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(UserGroup.SINGULAR, userGroupService.findOne(id));
        return frb;
    }

    @RequestMapping(value = {"/{id}/get_by_user/}", "/{id}/get_by_user"}, method = RequestMethod.GET)
    public Map getUserGroupByUser(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(User.SINGULAR, userService.getUserGroup(id));
        return frb;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Map createUserGroup(@RequestParam(required = false) String role, @RequestParam(required = false) String description) {
        HashMap<String, Object> frb = new HashMap<>();
        UserGroup userGroup = null;
        if (!StringUtils.isBlank(role)) {
            userGroup = userGroupService.create(role.toUpperCase(), description);
        }
        frb.put(UserGroup.SINGULAR, userGroup);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.POST)
    public Map updateUserGroup(@PathVariable long id, @RequestParam(required = false) String role,
                            @RequestParam(required = false) String description) {
        HashMap<String, Object> frb = new HashMap<>();
        UserGroup userGroup = null;
        if (!StringUtils.isBlank(role)) {
            userGroup = userGroupService.update(id, role.toUpperCase(), description);
        }
        frb.put(UserGroup.SINGULAR, userGroup);
        return frb;
    }

    @RequestMapping(value = {"/{id}/delete/", "/{id}/delete"}, method = RequestMethod.POST)
    public void deleteUserGroup(@PathVariable long id) {
        userGroupService.archive(id);
    }
}