///**
// *
// */
//package com.meizhou.mybatis.mapper;
//
//import com.meizhou.framework.orm.dao.mapper.IDivisionProMapper;
//import com.meizhou.framwork.common.BusinessException;
//import freemarker.template.Template;
//import org.apache.ibatis.builder.xml.XMLMapperBuilder;
//import org.apache.ibatis.session.Configuration;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//
//import java.io.ByteArrayInputStream;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.util.Set;
//
///**
// * @author hejun 自定义Mybatis框架加载
// */
//public class CustomSqlSessionFactoryBean extends SqlSessionFactoryBean {
//
//	private String dsname;
//
//	public String getDsname() {
//		return dsname;
//	}
//
//	public void setDsname(String dsname) {
//		this.dsname = dsname;
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		super.afterPropertiesSet();
//		SqlSessionFactory sqlSessionFactory = this.getObject();
//		Configuration configuration = sqlSessionFactory.getConfiguration();
//		createMapper(configuration);
//	}
//
//	freemarker.template.Configuration getConfiguration() {
//		freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_0);
//		configuration.setClassForTemplateLoading(this.getClass(), "/mapper");
//		return configuration;
//	}
//
//	/**
//	 * @param configuration
//	 *            Mybatis配置对象
//	 * @throws Exception
//	 */
//	protected void createMapper(Configuration configuration) throws Exception {
//		Set<Class<?>> foundClasses = ClassUtils.getClasses(MybatisConstants.BASE_PACKAGE);
//		for (Class<?> foundClass : foundClasses) {
//			if (dsname.equals(ClassUtils.getDataSourceName(foundClass))) {
//				String className = foundClass.getName();
//				String nopackageClassName = className.substring(className.lastIndexOf(".") + 1);
//				String poPackageName = className.substring(0, className.lastIndexOf("."));
//				String basePackageName = poPackageName.substring(0, poPackageName.lastIndexOf("."));
//				String mapperName = basePackageName + ".mapper" + "." + "I" + nopackageClassName + MybatisConstants.MAPPER_SUFFIX;
//				if (poPackageName.endsWith(".model.po")) {
//					basePackageName = poPackageName.substring(0, poPackageName.lastIndexOf(".model.po"));
//					mapperName = basePackageName + ".mapper" + "." + "I" + nopackageClassName + MybatisConstants.MAPPER_SUFFIX;
//				}
//				Class<?> mapperClazz;
//				try {
//					mapperClazz = Class.forName(mapperName);
//				} catch (ClassNotFoundException e) {
//					throw new BusinessException(e);
////				}
//				if (mapperClazz.getInterfaces()[0].getName().equals(IDivisionProMapper.class.getName())) {
//					MapperMeta mapperMeta = new MapperMeta(foundClass);
//					String mapperXml = renderDivisionProMapperXml(mapperMeta);
//					ByteArrayInputStream mapperXmlInputStream = new ByteArrayInputStream(mapperXml.getBytes());
//					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperXmlInputStream, configuration, mapperMeta.getMapperName(), configuration.getSqlFragments());
//					xmlMapperBuilder.parse();
//				} else if (mapperClazz.getInterfaces()[0].getName().equals(IDivisionProMapper.class.getName())) {
//					MapperMeta mapperMeta = new MapperMeta(foundClass);
//					String mapperXml = renderDivisionMapperXml(mapperMeta);
//					ByteArrayInputStream mapperXmlInputStream = new ByteArrayInputStream(mapperXml.getBytes());
//					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperXmlInputStream, configuration, mapperMeta.getMapperName(), configuration.getSqlFragments());
//					xmlMapperBuilder.parse();
//				} else {
//					MapperMeta mapperMeta = new MapperMeta(foundClass);
//					String mapperXml = renderCommonMapperXml(mapperMeta);
//					ByteArrayInputStream mapperXmlInputStream = new ByteArrayInputStream(mapperXml.getBytes());
//					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperXmlInputStream, configuration, mapperMeta.getMapperName(), configuration.getSqlFragments());
//					xmlMapperBuilder.parse();
//				}
//			}
//		}
//	}
//
//	public String renderDivisionProMapperXml(MapperMeta mapperMeta) throws Exception {
//		freemarker.template.Configuration configuration = getConfiguration();
//		Template template = configuration.getTemplate(MybatisConstants.DIVISION_PRO_MAPPER_TEMPLATE);
//		Writer writer = new StringWriter(1024);
//		template.process(mapperMeta, writer);
//		writer.close();
//		return writer.toString();
//	}
//
//	public String renderDivisionMapperXml(MapperMeta mapperMeta) throws Exception {
//		freemarker.template.Configuration configuration = getConfiguration();
//		Template template = configuration.getTemplate(MybatisConstants.DIVISION_MAPPER_TEMPLATE);
//		Writer writer = new StringWriter(1024);
//		template.process(mapperMeta, writer);
//		writer.close();
//		return writer.toString();
//	}
//
//	public String renderCommonMapperXml(MapperMeta mapperMeta) throws Exception {
//		freemarker.template.Configuration configuration = getConfiguration();
//		Template template = configuration.getTemplate(MybatisConstants.COMMON_MAPPER_TEMPLATE);
//		Writer writer = new StringWriter(1024);
//		template.process(mapperMeta, writer);
//		writer.close();
//		return writer.toString();
//	}
//
//}
