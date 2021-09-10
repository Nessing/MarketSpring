package ru.nessing.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.nessing.products.entities.Product;
import ru.nessing.products.repositories.ProductRepository;
import ru.nessing.products.services.ProductService;

import java.util.Optional;

@SpringBootTest(classes = {ProductService.class})
public class ProductsServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProduct() {
        Product product = new Product();
        product.setTitle("Axe");
        product.setPrice(32);
        product.setId(11L);

        Mockito.doReturn(Optional.of(product))
                .when(productRepository)
                .findById(11L);

        Product p = productService.findProductById(11L).get();
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(11L));
        Assertions.assertEquals("Axe", p.getTitle());
    }
}
