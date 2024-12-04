package br.com.digital.flavor.backend.product;

import br.com.digital.flavor.backend.product.dto.NewProductDto;
import br.com.digital.flavor.backend.product.dto.ProductCardDto;
import br.com.digital.flavor.backend.product.dto.ProductDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductCardDto>> getAll() {
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping("/get-all-by-name/{name}")
    public ResponseEntity<List<ProductCardDto>> getAllByName(@PathVariable String name) {
        return ResponseEntity.ok(this.productService.getAllByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody NewProductDto dto) {
        return ResponseEntity.ok(this.productService.save(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<Product> edit(@RequestBody Product product) {
        return ResponseEntity.ok(this.productService.edit(product));
    }

    @PostMapping("/save-with-file")
    public ResponseEntity<Product> saveWithFile(@RequestParam("product") String productJson,
                                              @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(this.productService.saveWithFile(new ObjectMapper().readValue(productJson, NewProductDto.class), file));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/edit-with-file")
    public ResponseEntity<Product> editWithFile(@RequestParam("product") String productJson,
                                              @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(this.productService.editWithFile(new ObjectMapper().readValue(productJson, Product.class), file));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductDetailsDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(this.productService.getById(id));
    }
}
