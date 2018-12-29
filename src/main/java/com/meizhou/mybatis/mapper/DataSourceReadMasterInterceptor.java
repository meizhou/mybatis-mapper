//package com.meizhou.mybatis.mapper;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
///**
// * Created by xiaohang on 2016/03/21
// */
//@Aspect
//public class DataSourceReadMasterInterceptor {
//
//    @Around("@annotation(com.meizhou.framework.orm.mybatis.DataSourceReadMaster)")
//    public Object printServiceLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            DataSourceReadMasterThreadLocal.add();
//            return joinPoint.proceed();
//        } finally {
//            DataSourceReadMasterThreadLocal.pop();
//        }
//    }
//
//}
