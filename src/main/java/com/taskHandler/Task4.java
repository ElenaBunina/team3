package com.taskHandler;


import org.apache.log4j.Logger;

public class Task4 implements ITask {

    private static final Logger LOGGER = Logger.getLogger("com.taskHandler");

    @Override
    public void execute() {
        System.out.println("метод 4");
        LOGGER.info("Task 4 launched");
    }
}
