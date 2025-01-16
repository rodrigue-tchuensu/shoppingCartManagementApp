package de.axontic.challenge.shoppingcartmanagement.logging;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    // Pointcut targeting all methods within @RestController classes
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerMethods() {}

    @After("controllerMethods()")
    public void logAfterControllerMethod(JoinPoint joinPoint) {
        // Get the current request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String method = request.getMethod(); // GET, POST, etc.
            String uri = request.getRequestURI(); // The accessed URI
            String handler = joinPoint.getSignature().toShortString(); // The method handler name
            log.info("Accessed endpoint: {} {}, Handler: {}", method, uri, handler);
        }
    }
}
