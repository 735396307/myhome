package com.yxl.myhome.po;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * @author BG343094
 * @since 2019/8/7 20:08
 **/
@Data
public class UserPO {
    private Long id;

    private String userName;

    private String password;

    private String aaa;
    private String aab;

    public static void main(String[] args) {



        int i=10000;
        long ts = System.currentTimeMillis();
        while ((i-->0)) {

            UserPO userPO = new UserPO();
            userPO.setId(1L);
            userPO.setUserName("27567567563");
            userPO.setPassword("123");
            userPO.setAaa("sdsdsds");
            userPO.setAab("sdsdsds");
            userPO.setUserName(""+ts);
            System.out.println(DigestUtils.md5Hex(JSON.toJSONString(userPO)));

        }
        System.out.println(System.currentTimeMillis() - ts);
    }
}
