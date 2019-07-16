package com.dao.mapper;

import com.annotation.ObjectType;
import com.model.Customer;
import com.model.Entity;
import com.model.Phone;
import com.model.Root;
import com.taskHandler.Task;
import com.taskHandler.Template;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper implements RowMapper<Entity> {

    private static final Logger LOGGER = Logger.getLogger("com.dao");

    Entity entity;

    public EntityMapper(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Class<? extends Entity> clazz = entity.getClass();
        if (clazz.isAnnotationPresent(ObjectType.class)) {
            ObjectType objectType = clazz.getAnnotation(ObjectType.class);
            if (objectType.value() == 2) {
                LOGGER.info("Create Root");
                entity = new Root();
            } else if (objectType.value() == 3) {
                LOGGER.info("Create Customer");
                entity = new Customer();
            } else if (objectType.value() == 4) {
                LOGGER.info("Create Phone");
                entity = new Phone();
            }else if (objectType.value() == 6) {
             entity = new Template();
                LOGGER.info("Create Template");
            }else if (objectType.value() == 7) {
                entity = new Task();
                LOGGER.info("Create Task");
        }
        }
        entity.setObjectId(rs.getLong("object_id"));
        entity.setParentId(rs.getLong("parent_id"));
        entity.setObjectTypeId(rs.getLong("object_type_id"));
        entity.setName(rs.getString("name"));
        LOGGER.info("Set all values from objects");
        return entity;
    }
}
