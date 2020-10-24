package com.biwanghuisuo.test.controller;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;

@Controller
public class CreateController {

    @RequestMapping("/toCreate")
    public String toCreate(){
        return "create";
    }

    @RequestMapping("/createClass")
    public void create(String controller, String method, String say, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        Template t = ve.getTemplate("BaseController.vm");
        VelocityContext ctx = new VelocityContext();

        String path2 = getClass().getResource("").getPath();
        String createPackage = path2.split("classes")[1];
        ctx.put("package", (createPackage.substring(1, createPackage.length() - 1)).replace("/", "."));
        ctx.put("className", controller);
        ctx.put("request", method);
        ctx.put("say", say);

        StringWriter sw = new StringWriter();

        t.merge(ctx, sw);

        String path = getClass().getResource("/").getPath();
        File file = new File(path);
        String orignalpath = new File(file.getParent()).getParent();
        orignalpath = orignalpath + "/src/main/java" + path2.split("classes")[1];
        FileOutputStream fos = new FileOutputStream(new File(orignalpath + controller + "Controller.java"));
        System.out.println(path + controller + "Controller.java");
        fos.write(sw.toString().getBytes());
        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().print(sw.toString());
    }
}
