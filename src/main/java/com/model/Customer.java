package com.model;

import com.annotation.Attribute;
import com.annotation.Name;
import com.annotation.ObjectType;
import com.annotation.Parent;

import java.util.List;
import java.util.Set;

@ObjectType(3)
@Parent(2)
@Name("Customer")
public class Customer extends Entity {

    @Attribute(attrId = 1, attrTypeId = 1)
    private String nameCust;
    @Attribute(attrId = 3, attrTypeId = 1)
    private String address;
    @Attribute(attrId = 4, attrTypeId = 1)
    private String telephone;
    @Attribute(attrId = 7, attrTypeId = 2)
    private Set<Long> phones;
    private long phone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNameCust() {
        return nameCust;
    }

    public void setNameCust(String nameCust) {
        this.nameCust = nameCust;
    }

    public Set<Long> getPhones() {
        return phones;
    }

    public void setPhones(Set<Long> phones) {
        this.phones = phones;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        Object[] fields = new Object[]{"Имя сотрудника: ", nameCust, "\nАдрес:", address,
                "\nНомер телефона: ", telephone};
        StringBuilder s = new StringBuilder();
        for (Object field : fields
                ) {
            s.append(field);
        }
        return s.toString();
    }
}
