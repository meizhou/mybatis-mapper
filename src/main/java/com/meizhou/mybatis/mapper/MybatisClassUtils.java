package com.meizhou.mybatis.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by xiaoshua on 2015/1/7.
 */
public class MybatisClassUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisClassUtils.class);

    public static String getPrimaryKeyName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                return field.getName();
            }
        }
        return "id";
    }

    public static String getShardKeyName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ShardKey.class)) {
                return field.getName();
            }
        }
        return null;
    }

    public static boolean needUpdate(Object po) {
        if (null == po) {
            return false;
        }
        Class<? extends Object> clazz = po.getClass();
        String primaryKeyName = getPrimaryKeyName(clazz);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(po);
                if (!primaryKeyName.equals(field.getName()) && !"serialVersionUID".equals(field.getName()) && null != fieldValue) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                throw new BusinessException(e);
            }
        }
        return false;
    }

    public static Object setPrimaryKeyValue(Object po) {
        if (null == po) {
            return null;
        }
        Class<? extends Object> clazz = po.getClass();
        Object primaryKeyValue = null;
        String primaryKeyColumn = MybatisClassUtils.getPrimaryKeyName(clazz);
        try {
            Field primaryKeyField = ReflectionUtils.findField(clazz, primaryKeyColumn);
            primaryKeyField.setAccessible(true);
            if (null == primaryKeyField.get(po)) {
                throw new BusinessException("主键[" + primaryKeyColumn + "]值未指定！");
            }
            primaryKeyValue = primaryKeyField.get(po);
            if (null == primaryKeyValue) {
                throw new BusinessException("主键[" + primaryKeyColumn + "]值为空！");
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        return primaryKeyValue;
    }

    public static Object getPrimaryKeyValue(Object po) {
        if (null == po) {
            return null;
        }
        Class<? extends Object> clazz = po.getClass();
        Object primaryKeyValue = null;
        String primaryKeyColumn = MybatisClassUtils.getPrimaryKeyName(clazz);
        try {
            Field primaryKeyField = ReflectionUtils.findField(clazz, primaryKeyColumn);
            primaryKeyField.setAccessible(true);
            primaryKeyValue = primaryKeyField.get(po);
            if (null == primaryKeyValue) {
                throw new BusinessException("主键[" + primaryKeyColumn + "]值为空！");
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        return primaryKeyValue;
    }

    public static Object getShardKeyValue(Object po) {
        if (null == po) {
            return null;
        }
        Class<? extends Object> clazz = po.getClass();
        Object shardKeyValue = null;
        String shardKeyName = MybatisClassUtils.getShardKeyName(clazz);
        if (null == shardKeyName) {
            throw new BusinessException("分表字段未配置！");
        }
        try {
            Field shardKeyField = ReflectionUtils.findField(clazz, shardKeyName);
            shardKeyField.setAccessible(true);
            shardKeyValue = shardKeyField.get(po);
            if (null == shardKeyValue) {
                throw new BusinessException("分表字段[" + shardKeyName + "]值为空！");
            }
        } catch (Exception e) {
            throw new BusinessException(e);
        }

        return shardKeyValue;
    }

    public static String getDataSourceName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        MapperConfig dataSource = clazz.getAnnotation(MapperConfig.class);
        if (null != dataSource) {
            return dataSource.dataSource();
        }
        return null;
    }

    public static Integer getTableSize(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        MapperConfig dataSource = clazz.getAnnotation(MapperConfig.class);
        if (null != dataSource) {
            return dataSource.tableSize();
        }
        return null;
    }


    public static Class<?> getPrimaryKeyType(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                return field.getType();
            }
        }
        return Integer.class;
    }

    public static Class<?> getShardKeyType(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ShardKey.class)) {
                return field.getType();
            }
        }
        return Integer.class;
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            // log.error("添加用户自定义视图类错误找不到此类的.class文件");
                                            LOGGER.error(e.getMessage(), e);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    // 添加到集合中去classes.add(Class.forName(packageName + '.'
                    // +className));
                    // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    public static String getMapperClazzName(Class<?> clazz) {
        String className = clazz.getName();
        String noPackageClassName = className.substring(className.lastIndexOf(".") + 1);
        String poPackageName = className.substring(0, className.lastIndexOf("."));
        String basePackageName = poPackageName.substring(0, poPackageName.lastIndexOf("."));
        String mapperName = basePackageName + ".mapper" + "." + "I" + noPackageClassName + MybatisConstants.MAPPER_SUFFIX;
        if (poPackageName.endsWith(".model.po")) {
            basePackageName = poPackageName.substring(0, poPackageName.lastIndexOf(".model.po"));
            mapperName = basePackageName + ".mapper" + "." + "I" + noPackageClassName + MybatisConstants.MAPPER_SUFFIX;
        }
        return mapperName;
    }

    public static String getTableName(Class<?> clazz) {
        String className = clazz.getName();
        String noPackageClassName = className.substring(className.lastIndexOf(".") + 1);
        return MybatisCamelCaseUtils.toUnderlineName(noPackageClassName);
    }

}
