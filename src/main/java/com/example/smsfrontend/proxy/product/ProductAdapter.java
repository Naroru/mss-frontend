package com.example.smsfrontend.proxy.product;

import com.example.smsfrontend.common.searchcriteria.SearchCriteria;
import com.example.smsfrontend.proxy.segment.Segment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "products", url = "http://localhost:8081/api/v1/products")
public interface ProductAdapter {

    @GetMapping("{id}")
    Product findById(@PathVariable("id") Long id);

    @GetMapping
    List<Product> findAll();

    @PostMapping("/spec")
    List<Product> findBySpec(@RequestBody SearchCriteria searchCriteria);

    @PostMapping
    Product save(@RequestBody Segment segment);

    @DeleteMapping("{id}")
    void setDeleted(@PathVariable Long id);
}
