package com.controller;

import com.dao.service.DAO;
import com.model.Entity;
import com.taskHandler.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    private static final Logger LOGGER = Logger.getLogger("com.controller");

    @Autowired
    private DAO dao;

    @RequestMapping(value = "/loadTemplate", method = RequestMethod.GET)
    public ModelAndView newTemplate (ModelAndView model) {
        List<Entity> listA = (List<Entity>) dao.loadTemplate();
        model.addObject("listEntity", listA);
        model.setViewName("newTemplate");
        return model;
    }


    @RequestMapping(value = "/newTask", method = RequestMethod.GET)
    public ModelAndView newTask ( ModelAndView model) {
        Task task = new Task();
        model.addObject("entity", task);
        model.setViewName("newTask");
        return model;
    }

//    @RequestMapping(value = "/loadTemplate", method = RequestMethod.GET)
//    public ModelAndView listParams(@RequestParam("id") long objectId, ModelAndView model) {
//        List<? extends Entity> list = dao.loadTemplate( objectId);
//        model.addObject("listParams", list);
//        model.setViewName("newTemplate");
//        return model;
//    }

    @RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public ModelAndView saveTask(@ModelAttribute("task") Task task) throws IllegalAccessException {
        dao.saveEntity(task);
        return new ModelAndView("redirect:/loadTemplate");
    }

//    @RequestMapping(value = "/saveTemplate", method = RequestMethod.POST)
//    public ModelAndView saveTemplate(@ModelAttribute("template") Template entity ) throws IllegalAccessException {
//        dao.saveEntity(entity);
//        return new ModelAndView("redirect:/home");
//    }


    @RequestMapping(value = "/newTask/{id}", method = RequestMethod.GET)
    public ModelAndView newTask (@PathVariable(value = "id") long id, ModelAndView model) {
        Task task = new Task();
        task.setPreviousTask(id);
        List<Entity> listA = (List<Entity>) dao.loadTemplate();
        model.addObject("listEntity", listA);
        model.addObject("entity", task);
        model.setViewName("newTask");
        return model;
    }


    @RequestMapping(value = "/saveTask/{id}", method = RequestMethod.POST)
    public ModelAndView saveTask(@PathVariable(value = "id") long id,
                                 @ModelAttribute("task") Task task) throws IllegalAccessException {
        Entity entity = dao.getEntityById(id);
        dao.saveEntityWithEntities(task,entity);
        return new ModelAndView("redirect:/home");
    }


}
