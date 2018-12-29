package com.meizhou.mybatis.mapper;

import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by meizhou on 2018/9/15.
 */
public class MainXml {

    static freemarker.template.Configuration getConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(MainXml.class.getClass(), "/mapper");
        return configuration;
    }


    public static void main(String[] args) throws Exception {
        freemarker.template.Configuration configuration = getConfiguration();
        Template template = configuration.getTemplate("common_mapper.ftl");
        Writer writer = new StringWriter(1024);
        template.process(mapperMeta, writer);
        writer.close();
        return writer.toString();
    }
}
