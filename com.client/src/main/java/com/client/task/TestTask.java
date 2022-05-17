package com.client.task;


import java.util.Date;
import java.util.concurrent.Callable;

public class TestTask implements Callable {
    public TestTask() {
        System.out.println("Create task");
    }

    @Override
    public Object call() throws Exception {
        return (new Date()).getTime();
    }
}
