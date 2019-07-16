package com.controller;

import com.dao.service.DAO;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


@Controller
public class MainController {

    private static final Logger LOGGER = Logger.getLogger("com.controller");

    @Autowired
    private DAO dao;

    @RequestMapping(value = "/home")
    public ModelAndView listEntity(ModelAndView model) throws IOException {
        List<Entity> listA = (List<Entity>) dao.getEntities();
        LOGGER.info("Return list of objects, /home");
        model.addObject("listEntity", listA);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam("objectId") long objectId, ModelAndView model) {
        Entity entityById = dao.getEntityById(objectId);
        LOGGER.info("Get entity, /edit");
        List<? extends Entity> list = dao.getParamsForEntity(entityById, objectId);
        LOGGER.info("Get list of params for entity, /edit");
        model.addObject("entityById", entityById);
        model.addObject("list", list);
        model.setViewName("editForm");
        return model;
    }

    @RequestMapping(value = "/saveEditRoot", method = RequestMethod.POST)
    public ModelAndView saveEdit(@ModelAttribute("entityById") Root entityById) throws IllegalAccessException {
        dao.updateEntity(entityById);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/saveEditCust", method = RequestMethod.POST)
    public ModelAndView saveEdit(@ModelAttribute("entityById") Customer entityById) throws IllegalAccessException {
        dao.updateEntity(entityById);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/saveEditPhone", method = RequestMethod.POST)
    public ModelAndView saveEdit(@ModelAttribute("entityById") Phone entityById) throws IllegalAccessException {
        dao.updateEntity(entityById);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/newEntity", method = RequestMethod.GET)
    public ModelAndView newEntity(ModelAndView model) {
        Root entity = new Root();
        model.addObject("entity", entity);
        model.setViewName("NewForm");
        return model;
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public ModelAndView newCustomer(ModelAndView model) {
        Customer entity = new Customer();
        model.addObject("entity", entity);
        model.setViewName("NewCustomerForm");
        return model;
    }

    @RequestMapping(value = "/newPhone/{id}", method = RequestMethod.GET)
    public ModelAndView newPhone(@PathVariable(value = "id") long id, ModelAndView model) {
        Phone phone = new Phone();
        if (id != 0) {
            phone.setRef(id);
        }
        model.addObject("phone", phone);
        model.setViewName("newPhone");
        return model;
    }

    @RequestMapping(value = "/savePhone/{id}", method = RequestMethod.POST)
    public ModelAndView savePhone(@PathVariable(value = "id") long id,
                                  @ModelAttribute("phone") Phone phone) throws IllegalAccessException {
        Entity entity = dao.getEntityById(id);
        dao.saveEntityWithEntities(entity, phone);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/newCustomerWithPhone", method = RequestMethod.GET)
    public ModelAndView newCustomerWithPhone(ModelAndView model) {
        Customer entity = new Customer();
        Phone phone = new Phone();
        model.addObject("entity", entity);
        model.addObject("phone", phone);
        model.setViewName("NewCustomerWithPhone");
        return model;
    }

    @RequestMapping(value = "/saveCustomerWithPhone", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer entity, @ModelAttribute("phone") Phone phone) throws IllegalAccessException {
        dao.saveEntityWithEntities(entity, phone);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/saveRoot", method = RequestMethod.POST)
    public ModelAndView saveRoot(@ModelAttribute Root entity) throws IllegalAccessException {
        dao.saveEntity(entity);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer entity) throws IllegalAccessException {
        dao.saveEntity(entity);
        return new ModelAndView("redirect:/home");
    }

//    @RequestMapping(value = "/object", method = RequestMethod.GET)
//    public ModelAndView listParams(@RequestParam("objectId") long objectId, ModelAndView model) {
//        Entity entity = new Entity();
//        List<? extends Entity> list = dao.getParamsForEntity(entity, objectId);
//        model.addObject("list", list);
//        model.setViewName("showParams");
//        return model;
//    }

    @RequestMapping(value = "/object", method = RequestMethod.GET)
    public ModelAndView listParams(@RequestParam("objectId") long objectId, ModelAndView model) {
        Entity entity = dao.getEntityById(objectId);
        List<? extends Entity> list = dao.getParamsForEntity(entity, objectId);
        model.addObject("listParams", list);
        model.setViewName("object");
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("objectId") long objectId) {
        dao.deleteEntity(objectId);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/createTemp", method = RequestMethod.POST)
    public ModelAndView createTemp(@ModelAttribute("obj") TempClass temp) throws IOException {
        List<Object> objects = temp.generateClass();
        File file = new File("D:\\downloads\\Test\\src\\main\\webapp\\resources\\newClass.java");
        FileWriter fileWriter = new FileWriter(file);
        StringBuilder s = new StringBuilder();
        for (Object field : objects
                ) {
            s.append(field);
        }
        String str = new String(s);
        fileWriter.write(str);
        fileWriter.close();
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/viewObjectType", method = RequestMethod.GET)
    public ModelAndView viewObjectType(@RequestParam("id") long id, ModelAndView model) throws IOException, JSONException {
        List<Attrib> Attributes = dao.getAttribute(id);
        List<ObjectTypeChild> objList = dao.getallObjectType();
        JSONArray array = new JSONArray();
        int node = 0;
        for (int i = 0; i < objList.size(); i++) {
            if (objList.get(i).getParentId() == 0) {
                JSONObject object = new JSONObject();
                object.put("text", objList.get(i).getName());
                object.put("obtypeId", objList.get(i).getId());
                object.put("href", "viewObjectType?id=" + objList.get(i).getId());
                object.put("nodes", dao.arraytoJSON(dao.getChild(objList.get(i).getId()), 0));
                array.put(node++, object);
            }
        }
        model.addObject("AttrList", Attributes);
        model.addObject("list", objList);
        model.addObject("parametr", id);
        model.addObject("size", objList.size());
        model.addObject("listContact", array.toString());
        model.setViewName("viewObjectType");
        return model;
    }

    @RequestMapping(value = "/createAttribute", method = RequestMethod.GET)
    public ModelAndView createAttribute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("ido"));
        ModelAndView model = new ModelAndView("ContactForm");
        model.addObject("id", id);
        model.addObject("type", 1);
        return model;
    }

    @RequestMapping(value = "/saveAttribute", method = RequestMethod.GET)
    public ModelAndView saveAttribute(HttpServletRequest request) {
        int ido = Integer.parseInt(request.getParameter("ido"));
        String name = request.getParameter("username");
        dao.saveAttribute(name, ido, dao.idlevel(1) + 1);
        return new ModelAndView("redirect:/viewObjectType?id=" + ido);
    }

    @RequestMapping(value = "/deleteAttribute", method = RequestMethod.GET)
    public ModelAndView deleteAttribute(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        int Id = Integer.parseInt(request.getParameter("ido"));
        dao.deleteAttribute(contactId, Id);
        return new ModelAndView("redirect:/viewObjectType?id=" + Id);
    }

    @RequestMapping(value = "/createObjecType", method = RequestMethod.GET)
    public ModelAndView createObjecType(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("ido"));
        ModelAndView model = new ModelAndView("ContactForm");
        model.addObject("type", 2);
        model.addObject("id", id);
        return model;
    }

    @RequestMapping(value = "/saveObjecType", method = RequestMethod.GET)
    public ModelAndView saveObjecType(HttpServletRequest request) {
        int paentId = Integer.parseInt(request.getParameter("objTypeId"));
        String name = request.getParameter("username");
        int level = dao.idlevel(2) + 1;
        dao.saveObjecType(name, paentId, level);
        return new ModelAndView("redirect:/viewObjectType?id=" + level);
    }
}

