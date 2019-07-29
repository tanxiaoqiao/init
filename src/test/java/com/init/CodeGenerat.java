package com.init;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class CodeGenerat {

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);

    @Test
    public void contextLoads() throws ClassNotFoundException, IOException, TemplateException {
        String entityName = "Role";
        String serviceName = "init";
        String projectPath = "/Users/xiaoqiao/workspace/init";

        String servicePath=projectPath; //projectPath + "/fire-" + serviceName +"-service";
        String modelPath= projectPath; //+ "/fire-" + serviceName +"-model";

        HashMap map = new HashMap();
        map.put("entity_name", entityName);
        map.put("entity_name_2", entityName.substring(0, 1).toLowerCase() + entityName.substring(1));
        map.put("service_name", serviceName);


        Class entityClass = Class.forName("com." + serviceName + ".entity." + entityName);

        System.err.println(entityClass);

        Field[] fieldsArray = entityClass.getDeclaredFields();
        List<Field> fieldsList = new LinkedList<Field>();
        for (Field field : fieldsArray) {
            if (!"serialVersionUID".equals(field.getName())) {
                fieldsList.add(field);
            }
        }

        map.put("fields", fieldsList);

        // 初始化引擎配置
        try {
            cfg.setDirectoryForTemplateLoading(new File(servicePath + "/src/main/resources/template"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        // 生成文件
        gen("Dto.ftl", map, modelPath + "/src/main/java/com/" + serviceName + "/model/" + entityName + "Dto.java");
        gen("Repository.ftl", map, servicePath + "/src/main/java/com/" + serviceName + "/repository/" + entityName + "Repository.java");
        gen("Service.ftl", map, servicePath + "/src/main/java/com/" + serviceName + "/service/" + entityName + "Service.java");
        gen("ServiceImpl.ftl", map, servicePath + "/src/main/java/com/" + serviceName + "/service/impl/" + entityName + "ServiceImpl.java");
        gen("Controller.ftl", map, servicePath + "/src/main/java/com/" + serviceName + "/controller/" + entityName + "Controller.java");

    }

    private void gen(String tempName, Map map, String outputpath) throws IOException, TemplateException {
        Template template = cfg.getTemplate(tempName);
        File file = new File(outputpath);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        template.process(map, out);
    }


}
