package za.co.study.casetshidiso.demoing.domain.model.product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    Product findProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
