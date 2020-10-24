package com.biwanghuisuo.test.controller;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestVelocity {

    private String get(){
        return getClass().getClassLoader().getResourceAsStream("/").toString();
    }

    public static void main(String[] args) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("BaseController.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put("className", "Test");
        ctx.put("request", "create");

//        List temp = new ArrayList();
//        temp.add("1");
//        temp.add("2");
//        ctx.put("list", temp);

        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(sw.toString());
    }
}
