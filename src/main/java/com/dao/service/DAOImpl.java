package com.dao.service;

import com.annotation.Attribute;
import com.annotation.Name;
import com.annotation.ObjectType;
import com.annotation.Parent;
import com.dao.extractor.EntityExtractor;
import com.dao.mapper.EntityMapper;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.model.*;
import com.taskHandler.Task;
import com.taskHandler.Template;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.jar.Attributes;

@SuppressWarnings("SqlDialectInspection")
public class DAOImpl implements DAO {

    private static final Logger LOGGER = Logger.getLogger("com.dao");

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public DAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public <T extends Entity> void updateEntity(T obj) {
        List<Field> fieldClass = getInheritedFields(obj.getClass());
        for (Field field : fieldClass) {
            if (field.isAnnotationPresent(Attribute.class)) {
                field.setAccessible(true);
                Attribute attribute = field.getAnnotation(Attribute.class);
                if (attribute.attrTypeId() != 2) {
                    Object value = null;
                    try {
                        value = field.get(obj);
                        String query = "UPDATE params SET value = ? WHERE object_id = ? AND attr_id = ?";
                        jdbcTemplate.update(query, new Object[]{value.toString(), obj.getObjectId(), attribute.attrId()});
                        LOGGER.info("Values updates successful");
                    } catch (IllegalAccessException e) {
                        LOGGER.warn("Object's field is NULL, Exception: ", e);
                    }
                }
            }
        }
    }

    @Override
    public List<? extends Entity> getEntities() {
        String sql = "SELECT object_id, parent_id, object_type_id, name FROM objects";
        List list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Entity.class));
        LOGGER.info("Return list of entities");
        return list;

    }

    @Override
    public void deleteEntity(long id) {
        String sql = "DELETE FROM objects WHERE object_id= ? ";
        jdbcTemplate.update(sql, id);
        LOGGER.info("Delete successful");
    }

    long generatorId() {
        long id = 0;
        long number = getJdbcTemplate().queryForLong("SELECT max(object_id) FROM objects");
        long a = Math.round(Math.random() * 1000);
        if (a != number && a != 0) {
            id = a;
        }
        return id;
    }

    @Override
    public <T extends Entity> void saveEntity(T obj) throws IllegalAccessException {
        long id = generatorId();
        Class clazz = obj.getClass();
        List<Field> fieldClass = getInheritedFields(obj.getClass());
        obj.setObjectId(id);
        if (clazz.isAnnotationPresent(ObjectType.class) && clazz.isAnnotationPresent(Parent.class)) {
            ObjectType objectType = (ObjectType) clazz.getAnnotation(ObjectType.class);
            Parent parent = (Parent) clazz.getAnnotation(Parent.class);
            Name name = (Name) clazz.getAnnotation(Name.class);
            String updateObject = "INSERT INTO objects (object_id, parent_id, object_type_id, name) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(updateObject, obj.getObjectId(), parent.value(), objectType.value(), name.value());
            for (Field field : fieldClass) {
                if (field.isAnnotationPresent(Attribute.class)) {
                    field.setAccessible(true);
                    Attribute attribute = field.getAnnotation(Attribute.class);
                    if (attribute.attrTypeId() != 2) {
                        Object objectValue = null;
                        objectValue = field.get(obj);
                        if (objectValue == null) continue;
                        String updateParams = "INSERT INTO params (attr_id, object_id, value) VALUES (?, ?, ?)";
                        jdbcTemplate.update(updateParams, attribute.attrId(), obj.getObjectId(), field.get(obj).toString());
                    }
                }
            }
        }
    }



    @Override
    public <T extends Entity> void saveEntityWithEntities(T obj, T phone) throws IllegalAccessException {
        long id;
        if (obj.getObjectId() == 0) {
            id = generatorId();
        } else {
            id = obj.getObjectId();
        }
        obj.setObjectId(id);
        Class clazz = obj.getClass();
        List<Field> fieldClass = getInheritedFields(obj.getClass());
        if (clazz.isAnnotationPresent(ObjectType.class) && clazz.isAnnotationPresent(Parent.class)) {
        for (Field field : fieldClass) {
                if (field.isAnnotationPresent(Attribute.class)) {
                    field.setAccessible(true);
                    Attribute attribute = field.getAnnotation(Attribute.class);
                    if (attribute.attrTypeId() == 1) {
                    } else if (attribute.attrTypeId() == 2) {
                       // long generatorId = generatorId();
                        setDataToObject(obj);
                       // saveEntity(phone);
                        String insertToRef = "INSERT INTO nc_references (attr_id, reference, object_id) VALUES (?, ?, ?)";
                        jdbcTemplate.update(insertToRef, attribute.attrId(), phone.getObjectId(), obj.getObjectId());
                    }
                }
            }
        }
    }


    private <T extends Entity> void setDataToObject(T obj) throws IllegalAccessException {
        Class cl = obj.getClass();
        List<Field> fieldClass = getInheritedFields(obj.getClass());
        long generatorId = generatorId();
        obj.setObjectId(generatorId);
        if (cl.isAnnotationPresent(ObjectType.class) && cl.isAnnotationPresent(Parent.class)) {
            ObjectType objectType = (ObjectType) cl.getAnnotation(ObjectType.class);
            Parent parent = (Parent) cl.getAnnotation(Parent.class);
            Name name = (Name) cl.getAnnotation(Name.class);
            String insertObject = "INSERT INTO objects (object_id, parent_id, object_type_id, name) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertObject, obj.getObjectId(), parent.value(), objectType.value(), name.value());
            for (Field field : fieldClass) {
                if (field.isAnnotationPresent(Attribute.class)) {
                    field.setAccessible(true);
                    Attribute attribute = field.getAnnotation(Attribute.class);
                    Object objectValue = field.get(obj);
                    if (objectValue == null) continue;
                    String updateParams = "INSERT INTO params (attr_id, object_id, value) VALUES (?, ?, ?)";
                    jdbcTemplate.update(updateParams, attribute.attrId(), obj.getObjectId(), field.get(obj).toString());
                }
            }
        }
    }

    private static List<Field> getInheritedFields(Class<?> type) {

        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    @Override
    public Entity getEntityById(long objectId) {

        String queryId = "SELECT o.object_type_id FROM objects o WHERE object_id = ?";
        Entity entity;
        Long objTypeId = getJdbcTemplate().queryForObject(queryId, new Object[]{objectId}, Long.class);

        String query = "select  o.object_id,\n" +
                "  o.parent_id,\n" +
                "  o.object_type_id,\n" +
                "  o.name\n" +
                "from  objects o\n" +
                "WHERE o.object_id = ?";

        if (objTypeId == 2) {
            entity = new Root();
            Entity root = getJdbcTemplate().queryForObject(query,
                    new Object[]{objectId},
                    new EntityMapper(entity)
            );
            return root;
        } else if (objTypeId == 3) {
            entity = new Customer();
            Entity customer = getJdbcTemplate().queryForObject(query,
                    new Object[]{objectId},
                    new EntityMapper(entity)
            );
            return customer;
        } else if (objTypeId == 4) {
            entity = new Phone();
            Entity phone = getJdbcTemplate().queryForObject(query,
                    new Object[]{objectId},
                    new EntityMapper(entity)
            );
            return phone;
        }      else if (objTypeId == 6) {
            entity = new Template();
            Entity template = getJdbcTemplate().queryForObject(query,
                    new Object[]{objectId},
                    new EntityMapper(entity)
            );
            return template;
        } else if (objTypeId == 7) {
            entity = new Task();
            Entity task = getJdbcTemplate().queryForObject(query,
                    new Object[]{objectId},
                    new EntityMapper(entity)
            );
            return task;
        }
        return null;
    }




    public  List<? extends Entity> loadTemplate () {
        String sql = "SELECT  o.object_id,o.parent_id,  o.object_type_id,\n" +
                "o.name\n" +
                "FROM objects o\n" +
                "WHERE o.parent_id = 6;";
        List list = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Entity.class));
        return list;
    }

//    public <T extends Entity> List<Entity> getEntityById(T obj, long object_id) {
//
//        Class cl = obj.getClass();
//        List<Entity> entities;
//        ObjectType objectType = (ObjectType) cl.getAnnotation(ObjectType.class);
//
//        String query = "select a.name, p.value from params p, attributes a" +
//                " where a.attr_id in (select a.attr_id from attr_object_types " +
//                "where object_type_id = ? )and p.attr_id = a.attr_id " +
//                "and p.object_id = ?";
//
////        entities = getJdbcTemplate().query(query, new Object[]{objectType.value(), object_id},
////                new BeanPropertyRowMapper(Entity.class));
//        entities = getJdbcTemplate().query(query, new Object[]{6, 942},
//                new BeanPropertyRowMapper(Entity.class));
//
//        return entities;
//    }

    @Override
    public <T extends Entity> List<? extends Entity> getParamsForEntity(T obj, long objectId) {
        Class cl = obj.getClass();
        List<? extends Entity> params;
        ObjectType objectType = (ObjectType) cl.getAnnotation(ObjectType.class);
        String sql = "SELECT\n" +
                "  o.object_id,\n" +
                "  o.parent_id,\n" +
                "  o.object_type_id,\n" +
                "  o.name,\n" +
                "  a.name as param,\n" +
                "  p.value,\n" +
                "  nc.reference\n" +
                "FROM objects o\n" +
                "     JOIN\n" +
                "       params p ON o.object_id = p.object_id\n" +
                "     JOIN\n" +
                "       attributes a ON p.attr_id = a.attr_id\n" +
                "     LEFT JOIN\n" +
                "       nc_references nc ON o.object_id = nc.object_id\n" +
                "WHERE a.attr_id IN (SELECT a.attr_id\n" +
                "                    FROM attr_object_types\n" +
                "                    WHERE object_type_id = ?)\n" +
                "      AND p.object_id = ?;\n";

        params = (List<? extends Entity>) getJdbcTemplate().query(sql, new Object[]{objectType.value(),
                objectId}, new EntityExtractor(obj));
        return params;
    }

    @Override
    public void saveAttribute(String name, int objectid, int attrid) {
        String sqlattr = "INSERT INTO attributes (name,attr_id)"
                + " VALUES (?,?)";
        String sqlotno = "INSERT INTO attr_object_types (object_type_id,attr_id)"
                + " VALUES (?,?)";
        jdbcTemplate.update(sqlattr, name, attrid);
        jdbcTemplate.update(sqlotno, objectid, attrid);

    }

    @Override
    public void saveObjecType(String name, int parentid, int level) {
        String sqlattr = "INSERT INTO object_types (name,parent_id,object_type_id)"
                + " VALUES (?,?,?)";
        jdbcTemplate.update(sqlattr, name, parentid, level);


    }

    @Override
    public void deleteAttribute(int attrId, int objectId) {
        String sql = "DELETE FROM attr_object_types WHERE object_type_id=? and attr_id=?";
        jdbcTemplate.update(sql, objectId, attrId);
    }

    @Override
    public int idlevel(int tableType) {
        if (tableType == 1) {
            String sql = "SELECT max(ATTR_ID) FROM ATTRIBUTES";
            int rowCount = this.jdbcTemplate.queryForObject(sql, Integer.class);
            return rowCount;
        } else {
            String sql = "SELECT max(object_type_id) FROM object_types";
            int rowCount = this.jdbcTemplate.queryForObject(sql, Integer.class);
            return rowCount;
        }


    }

    @Override
    public List<Attrib> getAttribute(long id) {
        String sql = "WITH RECURSIVE Rec AS (\n" +
                "SELECT object_type_id, name, parent_id, 0 as lavel, array[object_type_id] as pat_info FROM object_types\n" +
                "WHERE object_type_id = ?\n" +
                "UNION ALL\n" +
                "SELECT\n" +
                "t.object_type_id,\n" +
                "t.name,t.parent_id,\n" +
                "rt.lavel+1,rt.pat_info||t.object_type_id\n" +
                "  FROM object_types t\n" +
                "  JOIN Rec rt ON rt.parent_id= t.object_type_id)\n" +
                "\n" +
                "SELECT DISTINCT NAME,A.ATTR_ID\n" +
                "      FROM attributes A JOIN\n" +
                "      (SELECT ATTR_ID FROM (SELECT object_type_id FROM Rec ORDER BY pat_info) U,attr_object_types H\n" +
                "       LEFT JOIN object_types O ON O.OBJECT_TYPE_ID = H.OBJECT_TYPE_ID\n" +
                "       WHERE O.OBJECT_TYPE_ID=U.object_type_id) AS Y ON Y.ATTR_ID=A.ATTR_ID";

        List<Attrib> attributesList = jdbcTemplate.query(sql, new RowMapper<Attrib>() {
            @Override
            public Attrib mapRow(ResultSet rs, int rowNum) throws SQLException {
                Attrib Attrib = new Attrib();
                Attrib.setId(rs.getInt("ATTR_ID"));
                Attrib.setName(rs.getString("NAME"));
                return Attrib;
            }
        }, id);

        return attributesList;
    }

    @Override
    public List<ObjectTypeChild> getChild(int id) {

        String sql = "SELECT NAME,object_type_id, parent_id\n" +
                "FROM object_types\n" +
                "WHERE parent_id=?";
        List<ObjectTypeChild> ObjectTypeChildList = jdbcTemplate.query(sql, new RowMapper() {

            @Override
            public ObjectTypeChild mapRow(ResultSet rs, int rowNum) throws SQLException {
                ObjectTypeChild ObjectTypeChild = new ObjectTypeChild();
                ObjectTypeChild.setId(rs.getInt("object_type_id"));
                ObjectTypeChild.setParentId(rs.getInt("parent_id"));
                ObjectTypeChild.setName(rs.getString("name"));
                return ObjectTypeChild;
            }
        }, id);
        return ObjectTypeChildList;

    }

    @Override
    public List<ObjectTypeChild> getallObjectType() {

        String sql = "SELECT * FROM object_types";
        List<ObjectTypeChild> ObjectTypeChildList = jdbcTemplate.query(sql, new RowMapper() {

            @Override
            public ObjectTypeChild mapRow(ResultSet rs, int rowNum) throws SQLException {
                ObjectTypeChild ObjectTypeChild = new ObjectTypeChild();
                ObjectTypeChild.setId(rs.getInt("object_type_id"));
                ObjectTypeChild.setParentId(rs.getInt("parent_id"));
                ObjectTypeChild.setName(rs.getString("name"));
                return ObjectTypeChild;
            }
        });
        return ObjectTypeChildList;
    }

    @Override
    public JSONArray arraytoJSON(List<ObjectTypeChild> list, int level) throws JSONException {

        JSONArray result = new JSONArray();
        JSONArray array = new JSONArray();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
            List<ObjectTypeChild> childlist = new ArrayList<ObjectTypeChild>();

            childlist = getChild(list.get(i).getId());
            System.out.println(list.get(i).getName());
            if (childlist.size() == 0) {
                JSONObject object = new JSONObject();
                object.put("text", list.get(i).getName());
                object.put("obtypeId", list.get(i).getId());
                object.put("href", "viewObjectType?id=" + list.get(i).getId());
                result.put(level++, object);
            } else {
                JSONObject object = new JSONObject();
                object.put("text", list.get(i).getName());
                object.put("obtypeId", list.get(i).getId());
                object.put("href", "viewObjectType?id=" + list.get(i).getId());
                object.put("nodes", arraytoJSON(childlist, 0));
                result.put(level++, object);
            }
        }
        return result;
    }
}
