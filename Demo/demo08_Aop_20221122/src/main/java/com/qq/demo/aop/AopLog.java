package com.qq.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author longzhonghua
 * @data 2019/02/03 09:53
 * 使用@Before在切入点开始处切入内容
 * 使用@After在切入点结尾处切入内容
 * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */

/**
 * Description:  使之成为切面类
 */
@Aspect
/**
 * Description: 把切面类加入到IOC容器中
 */
@Component
@Slf4j
public class AopLog {
    //线程局部的变量,解决多线程中相同变量的访问冲突问题。
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //定义切点
    @Pointcut("execution(public * com.example..*.*(..))")
    public void aopWebLog() {
    }

    @Before("aopWebLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP方法 : " + request.getMethod());
        log.info("IP地址 : " + request.getRemoteAddr());
        log.info("类的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //log.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        log.info("参数 : " + request.getQueryString());
    }

    @AfterReturning(pointcut = "aopWebLog()", returning = "retObject")
    public void doAfterReturning(Object retObject) throws Throwable {
        // 处理完请求，返回内容
        log.info("应答值 : " + retObject);
        log.info("费时: " + (System.currentTimeMillis() - startTime.get()));
    }

    //抛出异常后通知（After throwing advice） ： 在方法抛出异常退出时执行的通知。
    @AfterThrowing(pointcut = "aopWebLog()", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        log.error("执行 " + " 异常", ex);
    }

}
