package com.yxl.myhome.user;

import com.yxl.myhome.po.UserPO;

/**
 * @author BG343094
 * @since 2019/8/29 21:23
 **/
public interface UserService {
    void create(UserPO userPO);

    void update(UserPO userPO);
    void update1(UserPO userPO);
}
