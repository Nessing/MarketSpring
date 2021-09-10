package ru.nessing.routing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nessing.routing.dtos.ProductDto;

import java.util.List;

@FeignClient("ms-product")
public interface ProductClient {

    @GetMapping("/api/v1/{id}")
    ProductDto findProductById(@PathVariable Long id);

    @GetMapping("/api/v1/ids")
    List<ProductDto> findProductsByIds(@RequestParam List<Long> ids);
}
