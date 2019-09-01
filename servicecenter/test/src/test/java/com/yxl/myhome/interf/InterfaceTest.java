package com.yxl.myhome.interf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BG343094
 * @since 2019/8/13 11:11
 **/
public class InterfaceTest {
    public static void main(String[] args) {
        List<String> ss = new ArrayList<>(Arrays.asList("123","234","343","434"));
        List<String> sub = ss.subList(1,4);
        sub.remove(0);
        sub.remove(0);
        System.out.println(ss.size());
        System.out.println(sub.size());
    }
}
