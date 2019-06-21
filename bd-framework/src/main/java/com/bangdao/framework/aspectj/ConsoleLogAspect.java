package com.bangdao.framework.aspectj;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;
@Aspect
@Component
@Slf4j
@Order(3)
public class ConsoleLogAspect {

    @Pointcut(value = "(execution(* com.bangdao.controller.*.*.*(..)))")
    public void webLog() {}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuilder sb = new StringBuilder();
        sb.append("\n请求路径:" + request.getRequestURL().toString() + "  " + request.getMethod() + "\n");
        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> paramMap = new HashMap<>();
            parameterMap.forEach((key, value) -> paramMap.put(key, Arrays.stream(value).collect(joining(","))));
            sb.append("请求内容:" + new Gson().toJson(paramMap));
        } else if (request.getMethod().equalsIgnoreCase(RequestMethod.POST.name())) {
            Object[] args = joinPoint.getArgs();
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(args).forEach(object -> stringBuilder.append(object.toString().replace("=",":")));
            if (stringBuilder.length() == 0){
                stringBuilder.append("{}");
            }
            sb.append("请求内容:" + stringBuilder.toString());
        }
        log.info(sb.toString());
    }

    @AfterReturning(pointcut = "webLog()",returning = "result")
    public void doAfterReturning(Object result) {
        if (ObjectUtils.isEmpty(result)){
            return;
        }
//        GsonBuilder builder = new GsonBuilder();
//
//        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
//            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                return new Date(json.getAsJsonPrimitive().getAsLong());
//            }
//        });
        log.info("\n返回結果:" + new Gson().toJson(result));
    }
}
