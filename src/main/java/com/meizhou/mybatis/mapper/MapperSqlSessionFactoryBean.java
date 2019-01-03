package com.meizhou.mybatis.mapper;

import freemarker.template.Template;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

/**
 * Created by meizhou on 2018/9/21.
 */
public class MapperSqlSessionFactoryBean extends SqlSessionFactoryBean {

    private String dsName;

    private String basePackage;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        SqlSessionFactory sqlSessionFactory = this.getObject();
        setMapperXml(sqlSessionFactory.getConfiguration());
    }

    private freemarker.template.Configuration getConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
        configuration.setClassForTemplateLoading(this.getClass(), "/freemarker_mapper");
        return configuration;
    }

    private String renderMapperXml(MapperMeta mapperMeta, String fileName) throws Exception {
        freemarker.template.Configuration configuration = getConfiguration();
        Template template = configuration.getTemplate(fileName);
        Writer writer = new StringWriter(1024);
        template.process(mapperMeta, writer);
        writer.close();
        return writer.toString();
    }

    private void setMapperXml(Configuration configuration) throws Exception {
        Set<Class<?>> foundClasses = MybatisClassUtils.getClasses(basePackage);
        for (Class<?> foundClass : foundClasses) {
            if (dsName.equals(MybatisClassUtils.getDataSourceName(foundClass))) {
                String mapperName = MybatisClassUtils.getMapperClazzName(foundClass);
                Class.forName(mapperName);
                MapperMeta mapperMeta = new MapperMeta(foundClass);
                String mapperXml;
                if (MybatisClassUtils.getTableSize(foundClass) == 1) {
                    mapperXml = renderMapperXml(mapperMeta, MybatisConstants.COMMON_MAPPER_TEMPLATE);
                } else {
                    mapperXml = renderMapperXml(mapperMeta, MybatisConstants.DIVISION_PRO_MAPPER_TEMPLATE);
                }
                ByteArrayInputStream mapperXmlInputStream = new ByteArrayInputStream(mapperXml.getBytes());
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperXmlInputStream, configuration, mapperMeta.getMapperName(), configuration.getSqlFragments());
                xmlMapperBuilder.parse();
            }
        }
    }
}