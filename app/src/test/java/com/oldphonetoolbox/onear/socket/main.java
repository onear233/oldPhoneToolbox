package com.oldphonetoolbox.onear.socket;

import org.junit.Test;

public class main {
    public static void main(String[] args) {
        System.out.println("开始测试");
        new Thread(new TestThread(new main())).start();
    }
    @Test
    public void sad(){
        System.out.println("开始测试");
        new Thread(new TestThread(new main())).start();
    }


    public void test(){
        System.out.println("测试");
    }
}
