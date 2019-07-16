package com.model;


import com.annotation.Attribute;
import com.annotation.Name;
import com.annotation.ObjectType;
import com.annotation.Parent;

@ObjectType(2)
@Parent(1)
@Name("Root")
public class Root extends Entity {

    @Attribute(attrId = 1, attrTypeId = 1)
    private String nameRoot;
    @Attribute(attrId = 2, attrTypeId = 1)
    private long cost;

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getNameRoot() {
        return nameRoot;
    }

    public void setNameRoot(String nameRoot) {
        this.nameRoot = nameRoot;
    }

    @Override
    public String toString() {
        Object[] fields = new Object[]{"Имя сотрудника: ", nameRoot, "\nCost:", cost};
        StringBuilder s = new StringBuilder();
        for (Object field:fields
                ) {
            s.append(field);
        }
        return s.toString();
    }
}
