package com.sensor.utils.transport;

import com.sensor.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTransportToService {

    private Product product;

    private MultipartFile file;
}
