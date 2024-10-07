package za.co.study.casetshidiso.service;

import jakarta.ws.rs.core.Response;
import za.co.study.casetshidiso.demoing.domain.model.product.Product;

public interface ProductService {

    Response findAllProducts();
    Response findProduct(long id);
    Response createProduct(Product product);
    Response updateProduct(Product product);
    Response deleteProduct(long id);
}
