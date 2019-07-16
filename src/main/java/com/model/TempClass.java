package com.model;

import java.util.ArrayList;
import java.util.List;

public class TempClass {

    private int objType;
    private int parentId;
    private String nameObj;
    private String type;
    private List<Integer> attrId;
    private List<Integer> attrTypeId;
    private List<String> nameField;

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNameObj() {
        return nameObj;
    }

    public void setNameObj(String nameObj) {
        this.nameObj = nameObj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getAttrId() {
        return attrId;
    }

    public void setAttrId(List<Integer> attrId) {
        this.attrId = attrId;
    }

    public List<Integer> getAttrTypeId() {
        return attrTypeId;
    }

    public void setAttrTypeId(List<Integer> attrTypeId) {
        this.attrTypeId = attrTypeId;
    }

    public List<String> getNameField() {
        return nameField;
    }

    public void setNameField(List<String> nameField) {
        this.nameField = nameField;
    }

    public List<Object> generateClass() {

        List<Object> constPart = new ArrayList<>();
        String start = "@ObjectType(" + objType + ")\n"
                + "@Parent(" + parentId + ")\n"
                + "@Name(" + "\"" + nameObj + "\"" + ")\n"
                + "public class " + nameObj + " extends Entity" + "{ " + "\n" + "\n";

        constPart.add(start);

        for (int i = 0; i < attrId.size(); i++) {
            if (attrId.get(i) == 1 || attrId.get(i) == 3 || attrId.get(i) == 5) {
                type = "String";
            } else if (attrId.get(i) == 2 || attrId.get(i) == 6 || attrId.get(i) == 7 || attrId.get(i) == 4) {
                type = "long";
            }
            String fields = "@Attribute(attrId = " + attrId.get(i)
                    + ", attrTypeId = " + 1/*attrTypeId*/ + ")\n"
                    + "private " + type + " " + nameField.get(i) + ";" + "\n" + "\n"
                    + "public " + type + " " + "get" + nameField.get(i) + "()" + "{\n"
                    + "return " + nameField.get(i) + ";\n" + "}\n" + "\n"
                    + "public void " + "set" + nameField.get(i) + "(" + type + " " + nameField.get(i) + ")"
                    + "{\n" + "this." + nameField.get(i) + " = " + nameField.get(i) + ";\n" + "}\n\n";
            constPart.add(fields);
        }

        String end = "\n" + "}";
        constPart.add(end);
        return constPart;
    }
}
