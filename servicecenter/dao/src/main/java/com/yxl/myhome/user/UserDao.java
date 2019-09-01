package com.yxl.myhome.user;

import com.yxl.myhome.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author BG343094
 * @since 2019/8/7 20:07
 **/
@Mapper
public interface UserDao {
    List<UserPO> getList();

    int create(UserPO userPO);

    void update(UserPO userPO);
}
