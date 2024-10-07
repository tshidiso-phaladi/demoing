package za.co.study.casetshidiso.demoing.domain.model.product;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import za.co.study.casetshidiso.demoing.exceptions.ProductNotFoundException;
import za.co.study.casetshidiso.demoing.exceptions.ZeroPriceException;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProductRepository {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAllProducts() {
        logger.info("Getting all products");
        return entityManager.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    public Product findProduct(long id) throws ProductNotFoundException {
        Product product = entityManager.find(Product.class, id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        logger.info("Getting product by id: " + id);
        return entityManager.find(Product.class, id);
    }

    public Product createProduct(Product product) throws ZeroPriceException {
        logger.info("Creating product: " + product);

        if (product.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            throw new ZeroPriceException("Price cannot be zero");
        }
        entityManager.persist(product);

        return product;
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {
        logger.info("Updating product: " + product);
        Product updatedProduct = entityManager.find(Product.class, product.getId());
        if (updatedProduct == null) {
            throw new ProductNotFoundException(product.getId());
        }
        return entityManager.merge(product);
    }

    public void deleteProduct(long id) throws ProductNotFoundException {
        logger.info("Deleting product: " + id);
        Product deletedProduct = entityManager.find(Product.class, id);
        if (deletedProduct == null) {
            throw new ProductNotFoundException(id);
        }
        entityManager.remove(deletedProduct);
    }
}
