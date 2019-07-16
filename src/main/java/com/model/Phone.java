package com.model;

import com.annotation.Attribute;
import com.annotation.Name;
import com.annotation.ObjectType;
import com.annotation.Parent;

@ObjectType(4)
@Parent(1)
@Name("Phone")
public class Phone extends Entity {

    @Attribute(attrId = 5, attrTypeId = 1)
    private String mark;
    @Attribute(attrId = 6, attrTypeId = 1)
    private long price;
    @Attribute(attrId = 8, attrTypeId = 2)
    private long ref;

    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mark='" + mark + '\'' +
                ", price=" + price +
                '}';
    }

}
