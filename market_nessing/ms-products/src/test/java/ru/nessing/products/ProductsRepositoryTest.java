package ru.nessing.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.nessing.products.entities.Product;
import ru.nessing.products.repositories.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductsRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initDbTest() {
        List<Product> list = productRepository.findAll();
        assertEquals("Tower", list.get(4).getTitle());
    }
}
