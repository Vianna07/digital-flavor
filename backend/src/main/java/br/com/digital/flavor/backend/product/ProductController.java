package br.com.digital.flavor.backend.product;

import br.com.digital.flavor.backend.product.dto.NewProductDto;
import br.com.digital.flavor.backend.product.dto.ProductCardDto;
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
    public ResponseEntity<Product> uploadFile(@RequestBody NewProductDto dto) {
        return ResponseEntity.ok(this.productService.save(dto));
    }

    @PostMapping("/save-with-file")
    public ResponseEntity<Product> uploadFile(@RequestParam("product") String productJson,
                                              @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(this.productService.saveWithFile(new ObjectMapper().readValue(productJson, NewProductDto.class), file));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
