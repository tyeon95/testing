package io.muic.ooc.webapp.controller;


import io.muic.ooc.webapp.api.entity.Follow;
import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.service.FollowService;
import io.muic.ooc.webapp.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyeon on 3/13/17.
 */
@RestController
@RequestMapping(value = "/"+ Follow.PLURAL)
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/{id}/get_by_user/", "/{id}/get_by_user"}, method = RequestMethod.GET)
    public Map getFollowByUser(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Follow.SINGULAR, userService.getFollowing(id));
        return frb;
    }

    @RequestMapping(value = {"/{id}/follow/{userId}", "/{id}/follow/{userId}"}, method = RequestMethod.GET)
    public Map follow(@PathVariable long id, @PathVariable long userId) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Follow.SINGULAR, followService.follow(id, userId));
        return frb;
    }

    @RequestMapping(value = {"/{id}/unfollow/{userId}", "/{id}/follow/{userId}"}, method = RequestMethod.GET)
    public Map unfollow(@PathVariable long id, @PathVariable long userId) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Follow.SINGULAR, followService.unfollow(id, userId));
        return frb;
    }
}
