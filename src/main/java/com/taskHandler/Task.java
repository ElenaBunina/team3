package com.taskHandler;

import com.annotation.Attribute;
import com.annotation.Name;
import com.annotation.ObjectType;
import com.annotation.Parent;
import com.model.Entity;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Set;


@ObjectType(7)
@Parent(6)
@Name("Task")
@EnableScheduling
public class Task extends Entity {
    @Attribute(attrId = 10, attrTypeId = 1)
    private String statusTask;
    @Attribute(attrId = 11, attrTypeId = 1)
    private String implementation;
    private Set<Long> previousTasks;
    @Attribute(attrId = 14, attrTypeId = 2)
    private long previousTask;


    public Set<Long> getPreviousTasks() {
        return previousTasks;
    }

    public void setPreviousTasks(Set<Long> previousTasks) {
        this.previousTasks = previousTasks;
    }

    public long getPreviousTask() {
        return previousTask;
    }

    public void setPreviousTask(long previousTask) {
        this.previousTask = previousTask;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    public void someMethod() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "statusTask='" + statusTask + '\'' +
                ", implementation='" + implementation + '\'' +
                ", previousTasks=" + previousTasks +
                ", previousTask=" + previousTask +
                '}';
    }
}
