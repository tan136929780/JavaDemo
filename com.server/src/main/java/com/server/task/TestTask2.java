package com.server.task;


public class TestTask2 implements Runnable {
    private String wbs;

    public TestTask2(String wbs) {
        this.wbs = wbs;
    }

    @Override
    public void run() {
        System.out.println(wbs);
    }
}
