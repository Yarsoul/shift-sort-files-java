package com.task;

import com.task.controller.MyController;

public class Main {
    public static void main(String[] args) {
        MyController controller = new MyController();
        controller.start(args);
    }
}