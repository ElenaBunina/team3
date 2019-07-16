package com.model;

import com.annotation.Attribute;
import com.annotation.Name;
import com.annotation.ObjectType;
import com.annotation.Parent;

@ObjectType(1)
@Parent(0)
@Name("Entity")
public class Entity {

    private long objectId;
    private long parentId;
    private long objectTypeId;
    private String name;

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(long objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        Object[] fields = new Object[]{"Id: ", objectId, "\nParentId:", parentId,
                "\nObjectTypeId: ", objectTypeId, "\nName: ", name};
        StringBuilder s = new StringBuilder();
        for (Object field:fields
                ) {
            s.append(field);
        }
        return s.toString();
    }
}

