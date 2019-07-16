package com.taskHandler;


import org.apache.log4j.Logger;


public class Task2 implements ITask{
    private static final Logger LOGGER = Logger.getLogger("com.taskHandler");

    @Override
    public void execute() {
        LOGGER.info("Task 2 launched");
        System.out.println("метод 2");
    }
}
