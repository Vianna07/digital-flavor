package br.com.digital.flavor.backend.product;

import br.com.digital.flavor.backend.security.tenant.CanteenContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.io.File.createTempFile;

@Service
public class ProductService {

    @Value("${apps.file-storage.url}")
    private String FILE_STORAGE_URL;

    @Autowired
    private ProductRepository productRepository;

    public Product save(NewProductDto dto) {
        Product product = new Product(dto);
        return this.productRepository.save(product);
    }

    public Product saveWithFile(NewProductDto dto, MultipartFile file) {
        Product product = new Product(dto);

        File renamedFile = createTempFile(file, product.getId() + ".png");

        if (renamedFile == null) {
            throw new RuntimeException("Erro ao criar arquivo temporário");
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("canteenId", CanteenContext.getCurrentCanteenId());
        body.add("file", new FileSystemResource(renamedFile));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    FILE_STORAGE_URL + "/s3/upload",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException( "Falha ao realizar upload da imagem de produto: " + response.getStatusCode());
            }

            product.setImageUrl(response.getBody());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return this.productRepository.save(product);
    }

    private File createTempFile(MultipartFile file, String newFileName) {
        try {
            String prefix = newFileName.substring(0, newFileName.lastIndexOf('.'));
            String suffix = newFileName.substring(newFileName.lastIndexOf('.'));
            File tempFile = File.createTempFile(prefix, suffix);

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(file.getBytes());
            }

            return tempFile;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
