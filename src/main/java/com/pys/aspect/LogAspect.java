package com.pys.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//日志切面处理

/**
 * @Aspect 注解： AOP切面作用
 * @Component 注解：开启组件扫描，通过注解找到要扫描的对象
 * @Pointcut("execution(*com.star.controller..(..))") ：定义切面，申明log()是一个切面，
 * 通过execution来表示需要拦截的类，这里表示拦截控制器下面的所有类所有方法
 * RequestLog：将请求的参数封装成一个内部类
 * 在访问页面（controller）之前，拦截请求的URL、IP、调用的方法、传递的参数、返回的内容，并记录到日志
 */

@Aspect
@Component
public class LogAspect {
    //获取日志信息
    private  final Logger logger =LoggerFactory.getLogger(this.getClass());
    //定义切面
    @Pointcut("execution(* com.pys.controller.*.*(..))")
    public void log(){}

    //在切面之前执行
    @Before(("log()"))
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取URL，IP
        String url = request.getRequestURI().toString();
        String ip = request.getRemoteAddr();
        //获取请求方法
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }

    //在切面之后执行
    @After("log()")
    public void doAfter() {
//        logger.info("--------doAfter--------");
    }

    //返回之后拦截
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }

    //封装请求参数
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod,Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    '}';
        }
    }
}
