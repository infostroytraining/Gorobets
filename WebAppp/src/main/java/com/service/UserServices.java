package com.service;

import com.entity.User;

import java.util.Map;

/**
 * Created by invincible_ g_d on 12/14/15.
 */
public interface UserServices {
    User add(User user);
    Map<String, String> getEmailForEachUser();
}
