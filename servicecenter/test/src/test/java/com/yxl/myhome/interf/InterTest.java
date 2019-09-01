package com.yxl.myhome.interf;

/**
 * @author BG343094
 * @since 2019/8/13 11:09
 **/
@FunctionalInterface
public interface InterTest {
    InterTest NONE = (value) -> value;

    String fun(String s);
}
