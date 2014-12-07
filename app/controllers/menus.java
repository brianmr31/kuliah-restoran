package controllers;

import java.lang.reflect.Constructor;
import java.util.List;

import models.menu;
import models.realresep;
import models.resep;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import controllers.CRUD.ObjectType;

public class menus extends CRUD {
	public static void blank() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Model object = (Model) constructor.newInstance();
        try {
            render(type, object);
        } catch (TemplateNotFoundException e) {
            render("CRUD/blank.html", type, object);
        }
    }
}
