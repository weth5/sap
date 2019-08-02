package com.whx.dubboService;

import com.whx.pojo.User;

/**
 * Created by Administrator on 2019/7/24.
 */
public interface UserService {
    String login(String username, String password);
    User findUserByName(String username);
}
