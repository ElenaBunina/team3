package com.taskHandler;


import org.apache.log4j.Logger;


public class Task1 implements ITask {
    private static final Logger LOGGER = Logger.getLogger("com.taskHandler");

    @Override
    public void execute() {
        LOGGER.info("Task 1 launched");
        System.out.println("метод 1");
    }

}
