package com.msb.apipassenger;

/**
 * @Author:maojianfeng
 * @Date:2023-04-15-19:11
 * @Description:
 * @version:1.0
 */
public class Test {
    public static void main(String[] args) {
        //生成随机5个字符
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        //从10到100的for循环
        for (int i = 10; i < 100; i++) {
            //生成一个随机数
            int random = (int) (Math.random() * str.length());
            //截取随机数的第一个字符
            String s = str.substring(random, random + 1);
            //打印
            System.out.print(s);
        }


    }
}
