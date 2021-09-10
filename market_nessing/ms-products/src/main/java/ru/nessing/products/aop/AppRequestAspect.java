package ru.nessing.products.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.nessing.products.entities.Product;

@Aspect
@Component
public class AppRequestAspect {
    @Before("execution(public * ru.nessing.products.controllers.ProductController.findAllProducts(..))")
    public void beforeGetfindAllProducts() {
        System.out.println("Запрос на получение списка всех товаров");
    }

    @Before("execution(public * ru.nessing.products.controllers.ProductController.findProductById(..))")
    public void beforeGetFindProductById(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Запрос на получение товара по ID: " + args[0]);
    }

    @Before("execution(public * ru.nessing.products.controllers.ProductController.saveNewProduct(..))")
    public void beforePostSaveNewProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Product product = (Product) args[0];
        System.out.printf("Товар добавлен:\nid: %s\ntitle: %s\ncost: %s\n", product.getId(), product.getTitle(), product.getPrice());
    }

    @Before("execution(public * ru.nessing.products.controllers.ProductController.updateProduct(..))")
    public void beforePutUpdateProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Product product = (Product) args[0];
        System.out.printf("Товар добавлен:\nid: %s\ntitle: %s\ncost: %s\n", product.getId(), product.getTitle(), product.getPrice());
    }

    @Before("execution(public * ru.nessing.products.controllers.ProductController.deleteProduct(..))")
    public void beforeDeleteProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println("Удален товар с ID: " + args[0]);
    }
}
