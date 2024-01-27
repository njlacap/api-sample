package project.sample.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.sample.api.dto.ProductDto;
import project.sample.api.exception.ProductException;
import project.sample.api.service.impl.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImplInject) {
        this.productServiceImpl = productServiceImplInject;
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "id") Long id) throws ProductException {
        log.info("ProductController - getProductById - Invoked");
        return new ResponseEntity<>(productServiceImpl.getProductDtoById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getAllProduct() throws ProductException {
        log.info("ProductController - getAllProduct - Invoked");
        return new ResponseEntity<>(productServiceImpl.getAllProduct(), HttpStatus.FOUND);
    }

}
