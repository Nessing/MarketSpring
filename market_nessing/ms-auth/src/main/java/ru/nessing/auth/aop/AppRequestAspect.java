package ru.nessing.auth.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppRequestAspect {

    @Before("execution(public void ru.nessing.auth.controllers.AuthController.registerUser(*))")
    public void beforeAuth() {
        System.out.println("Пользователь авторизировался");
    }
}
