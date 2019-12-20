package com.imooc.monitor.aop;

import com.imooc.monitor.annotation.DataSource;
import com.imooc.monitor.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017/9/16 22:20
 */
@Aspect
@Slf4j
@Component
public class DataSourceAspect implements Ordered {

    @After("execution(* com.imooc.monitor.service..*.*(..))")
    public void after(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSource();
    }

    @Pointcut("@annotation(com.imooc.monitor.annotation.DataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            DynamicDataSourceContextHolder.setDataSource("center");
        } else {
            DynamicDataSourceContextHolder.setDataSource(ds.value());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSource();
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
