package com.hrh.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/21 17:41
 * 对日志管理的切面操作，哪些东西放进日志里，哪些不放
 * @Aspect开启切面,然后才能进行切面操作
 * @Component开启组件扫描，使IOC容器能够找到该类
 * @Pointcut定义切面 参数是规定这个切面拦截哪些类
 * 定义一个切入点表达式,用来确定哪些类需要代理
 * execution(* com.hrh.blog.web.*.*(..))代表com.hrh.blog.web包下所有类的所有方法都会被代理
 */
@Aspect
@Component
public class LogAspect {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    //定义切面  该方法什么都不用写，加上注解声明它是一个切面即可  execution里的内容规定切面拦截哪些类
    @Pointcut("execution(* com.hrh.blog.web.*.*(..))")
    public void log(){}
    //切面前记录
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        LOG.info("------------doBefore-------------");
        //获取用户的id以及访问url
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = String.valueOf(request.getRequestURL());
        String ip = request.getRemoteAddr();
        //获取用户访问的类和类里的方法名 通过JoinPoint 获取
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        ResultLog resultLog = new ResultLog(url,ip,classMethod,args);
        LOG.info("request : {}",resultLog);
    }
    //切面后记录
    @After("log()")
    public void doAfter(){
        LOG.info("------------doAfter-------------");
    }
    //记录切面后返回的内容
    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result){
        LOG.info("Result : {}",result);
    }
    //定义一个内部类，为了方便输出
    private static class ResultLog{
        //访问url
        private String url;
        //用户ip
        private String ip;
        //用户访问的类
        private String classMethod;
        //传递的参数  因为不确定参数类型以及参数的个数所以用Object数组（主要还是切面方法获取到的参数是数组类型。。。）
        private  Object[] args;
        //定义构造方法，方便赋值
        public ResultLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "ResultLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
