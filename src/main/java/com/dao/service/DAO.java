package com.dao.service;


import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.model.*;

import java.util.List;

public interface DAO {

    List<? extends Entity> getEntities();

    Entity getEntityById(long object_id);

    <T extends Entity> void saveEntity(T obj) throws IllegalAccessException;

    <T extends Entity> void saveEntityWithEntities(T obj, T phone) throws IllegalAccessException;

    void deleteEntity(long id);

    <T extends Entity> List<? extends Entity> getParamsForEntity(T obj, long id);

    <T extends Entity> void updateEntity(T obj) throws IllegalAccessException;

    void saveAttribute(String name, int objectid, int attrid);

    void saveObjecType(String name, int parentid, int level);

    void deleteAttribute(int attrId, int objectId);

    int idlevel(int tableType);

    public  List<? extends Entity> loadTemplate ();

    List<Attrib> getAttribute(long id);

    List<ObjectTypeChild> getChild(int id);

    List<ObjectTypeChild> getallObjectType();

    JSONArray arraytoJSON(List<ObjectTypeChild> list, int level) throws JSONException;

}
