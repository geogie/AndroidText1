package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class ExampleDaoGenerator {
    public static void main(String[] args) throws Exception{
        Schema schema = new Schema(1, "greendao");
        schema.setDefaultJavaPackageDao("com.guangda.dao");
        Entity userBean = schema.addEntity("Users");
        userBean.setTableName("Users");
        userBean.addIdProperty();
        userBean.addStringProperty("uSex");
        userBean.addStringProperty("uTelphone");
        userBean.addStringProperty("uAge");
        userBean.addStringProperty("uName");
        new DaoGenerator().generateAll(schema, args[0]);
    }
    private static void addNote(Schema schema){
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }
}
