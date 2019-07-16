package com.dao.extractor;

import com.model.Customer;
import com.model.Entity;
import com.model.Phone;
import com.model.Root;
import com.taskHandler.Task;
import com.taskHandler.Template;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EntityExtractor implements ResultSetExtractor {

    private static final Logger LOGGER = Logger.getLogger("com.dao");

    private Entity entity;

    public EntityExtractor(Entity entity) {
        this.entity = entity;
    }

    @Override
    public List<? extends Entity> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Entity> entityList = new ArrayList<>();
        Set<Long> refSet = new HashSet<>();
        try {
            if (entity == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            LOGGER.warn("entity is null, Exception: ", e);
        }
        Class<? extends Entity> clazz = entity.getClass();
        Field field = null;
        Field field1 = null;
        try {
            field = clazz.getDeclaredField("phones");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            LOGGER.warn("Exception not field, Exception: ", e);
        }
        try {
            field1 = clazz.getDeclaredField("previousTasks");
            field1.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        while (rs.next()) {
            //общая часть, эти значения для всех сущностей
            entity.setObjectId(rs.getInt("object_id"));
            entity.setParentId(rs.getInt("parent_id"));
            entity.setObjectTypeId(rs.getInt("object_type_id"));
            entity.setName(rs.getString("name"));
            //теперь проверяем, если наша сущность root
            if (entity instanceof Root) {
                try {
                    if (rs.getString("param").equals("name")) {
                        Method setNameRoot = clazz.getMethod("setNameRoot", new Class[]{String.class});
                        setNameRoot.invoke(entity, rs.getString("value"));
                        LOGGER.info("NameRoot set");
                    }
                    if (rs.getString("param").equals("cost")) {
                        Method setCost = clazz.getMethod("setCost", new Class[]{long.class});
                        setCost.invoke(entity, rs.getLong("value"));
                        LOGGER.info("Cost set");
                    }
                } catch (NoSuchMethodException e) {
                    LOGGER.warn("Not found method for Root, Exception: ", e);
                } catch (IllegalAccessException e) {
                    LOGGER.warn("Permission denied Root, Exception: ", e);
                } catch (InvocationTargetException e) {
                    LOGGER.warn("Exception #3 Root, Exception: ", e);
                }
            }
            // если наша сущность - customer
            else if (entity instanceof Customer) {
                try {
                    if (rs.getString("param").equals("name")) {
                        Method setNameCust = clazz.getDeclaredMethod("setNameCust", new Class[]{String.class});
                        setNameCust.invoke(entity, rs.getString("value"));
                        LOGGER.info("NameCust set");
                    }
                    if (rs.getString("param").equals("address")) {
                        Method setAddress = clazz.getDeclaredMethod("setAddress", new Class[]{String.class});
                        setAddress.invoke(entity, rs.getString("value"));
                        LOGGER.info("Address set");
                    }
                    if (rs.getString("param").equals("telephone")) {
                        Method setTelephone = clazz.getDeclaredMethod("setTelephone", new Class[]{String.class});
                        setTelephone.invoke(entity, rs.getString("value"));
                        LOGGER.info("Telephone set");
                    }
                    if (rs.getLong("reference") != 0) {
                        refSet.add(rs.getLong("reference"));
                        LOGGER.info("Phone set");
                    } else if (rs.getLong("reference") == 0) {
                        Method setPhone = clazz.getDeclaredMethod("setPhone", new Class[]{long.class});
                        setPhone.invoke(entity, 0);
                        LOGGER.info("Phone set = 0");
                    }
                    field.set(entity, refSet);
                } catch (NoSuchMethodException e) {
                    LOGGER.warn("Not found method for Customer, Exception: ", e);
                } catch (IllegalAccessException e) {
                    LOGGER.warn("Permission denied Customer, Exception: ", e);
                } catch (InvocationTargetException e) {
                    LOGGER.warn("Exception #3 Customer, Exception: ", e);
                }
            }
            //если наша сущность телефон
            else if (entity instanceof Phone) {
                try {
                    Method setMark = clazz.getMethod("setMark", new Class[]{String.class});
                    if (rs.getString("param").equals("mark")) {
                        setMark.invoke(entity, rs.getString("value"));
                        LOGGER.info("Mark set");
                    }
                    Method setPrice = clazz.getMethod("setPrice", new Class[]{long.class});
                    if (rs.getString("param").equals("price")) {
                        setPrice.invoke(entity, rs.getLong("value"));
                        LOGGER.info("Price set");
                    }
                } catch (NoSuchMethodException e) {
                    LOGGER.warn("Not found method for Phone, Exception: ", e);
                } catch (IllegalAccessException e) {
                    LOGGER.warn("Permission denied Phone, Exception: ", e);
                } catch (InvocationTargetException e) {
                    LOGGER.warn("Exception #3 Phone, Exception: ", e);
                }
            }

            // если наша сущность - Template
            else if (entity instanceof Template) {
                try {
                    if (rs.getString("param").equals("nameTemplate")) {
                        Method setNameTemplate = clazz.getDeclaredMethod("setNameTemplate", new Class[]{String.class});
                        setNameTemplate.invoke(entity, rs.getString("value"));
                        LOGGER.info("NameTemplate set");
                    }
//                    if (rs.getLong("reference") != 0) {
//                        refSet.add(rs.getLong("reference"));
//                        LOGGER.info("Task set");
//                    } else if (rs.getLong("reference") == 0) {
//                        Method setTask = clazz.getDeclaredMethod("setTask", new Class[]{long.class});
//                        setTask.invoke(entity, 0);
//                        LOGGER.info("Task set = 0");
//                    }
//                    field1.set(entity, refSet);
                } catch (NoSuchMethodException e) {
                    System.out.println("Not found method for Template");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            //если наша сущность Task
            else if (entity instanceof Task) {
                try {
                    Method setStatusTask = clazz.getMethod("setStatusTask", new Class[]{String.class});
                    if (rs.getString("param").equals("statusTask")) {
                        setStatusTask.invoke(entity, rs.getString("value"));
                    }
                    Method setImplementation = clazz.getMethod("setImplementation", new Class[]{String.class});
                    if (rs.getString("param").equals("implementation")) {
                        setImplementation.invoke(entity, rs.getString("value"));
                    }
                    if (rs.getLong("reference") != 0) {
                        refSet.add(rs.getLong("reference"));
                        field1.set(entity, refSet);
                    } else if (rs.getLong("reference") == 0) {
//                        Method setPreviousTask = clazz.getDeclaredMethod("setPreviousTask", new Class[]{long.class});
//                        setPreviousTask.invoke(entity,   0);
                        Method setPreviousTask = clazz.getDeclaredMethod("setPreviousTask", new Class[]{long.class});
                        setPreviousTask.invoke(entity,   0);
                    }

//                    if (rs.getString("param").equals("previousTasks")) {
//                        Method setPreviousTask = clazz.getMethod("setPreviousTask", new Class[]{String.class});
//                        setPreviousTask.invoke(entity, rs.getString("value"));
//                    }

                } catch (NoSuchMethodException e) {
                    LOGGER.warn("Not found method for Task, Exception: ", e);
                } catch (IllegalAccessException e) {
                    LOGGER.warn("Permission denied Task, Exception: ", e);
                } catch (InvocationTargetException e) {
                    LOGGER.warn("Exception #3 Task, Exception: ", e);
                }
            }

        }
        entityList.add(entity);
        return entityList;
    }
}