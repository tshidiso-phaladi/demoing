package za.co.study.casetshidiso.demoing.persistence.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import za.co.study.casetshidiso.demoing.domain.model.product.Product;
import za.co.study.casetshidiso.demoing.domain.model.product.ProductRepository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class JpaProductRepository implements ProductRepository {

    private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext/*(unitName = "product")*/
    private EntityManager entityManager;

    @Override
    public List<Product> findAllProducts() {
        logger.info("Getting all products");
        return entityManager.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    @Override
    public Product findProduct(Long id) {
        logger.info("Getting product by id: " + id);
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("Creating product: " + product);
        //validation check if price is not less than or equal to zero
        entityManager.persist(product);

        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        logger.info("Updating product: " + product);
        Product updatedProduct = entityManager.find(Product.class, product.getId());
        if (updatedProduct != null) {
            return entityManager.merge(product);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("Deleting product: " + id);
        Product deletedProduct = entityManager.find(Product.class, id);
        if (deletedProduct != null) {
            entityManager.remove(deletedProduct);
        }
    }
}
