package com.dao.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class TaskProcessor {

    public TaskProcessor(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Scheduled(fixedDelay = 10000)
    public void doSomething() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        List<Integer> listRefById = new ArrayList<>();
        String query = "SELECT DISTINCT o.object_id\n" +
                "FROM objects o\n" +
                "JOIN params p ON o.object_id = p.object_id\n" +
                "  LEFT JOIN (\n" +
                "    SELECT *\n" +
                "      FRom nc_references nc\n" +
                "     ) nc ON nc.reference = o.object_id\n" +
                "WHERE  p.value IN ('accepted')";

        List<Map<String, Object>> maps = getJdbcTemplate().queryForList(query);
        for (Map x : maps) {
            Object reference = x.get("object_id");
            listRefById.add((Integer) reference);
        }

        for (int i = 0; i < maps.size(); i++) {
            Object o = maps.get(i).get("object_id");
            String className = (String) getJdbcTemplate().queryForObject(
                    "SELECT value FROM params WHERE attr_id =11 AND object_id= " + o, new Object[]{}, String.class);
            Class aclass = Class.forName(className);
            Method method = aclass.getMethod("execute");
            method.invoke(aclass.newInstance());
           // String update = "UPDATE params SET value = 'completed' WHERE object_id = " + o +" AND attr_id = 10";
           // jdbcTemplate.update(update);

        }
    }
}

