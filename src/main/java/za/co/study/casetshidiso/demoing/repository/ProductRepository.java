package za.co.study.casetshidiso.demoing.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import za.co.study.casetshidiso.demoing.model.entity.Product;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProductRepository {
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    //revert if not working
    @PersistenceContext/*(unitName = "product")*/
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        LOG.info("Getting all products");
        return entityManager.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    public Product getProductById(Long id) {
        LOG.info("Getting product by id: " + id);
        return entityManager.find(Product.class, id);
    }

    public Product createProduct(Product product) {
        LOG.info("Creating product: " + product);
        //validation check if price is not less than or equal to zero
        entityManager.persist(product);

        return product;
    }

    public Product updateProduct(Product product) {
        LOG.info("Updating product: " + product);
        Product updatedProduct = entityManager.find(Product.class, product.getId());
        if (updatedProduct != null) {
            return entityManager.merge(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        LOG.info("Deleting product: " + id);
        Product deletedProduct = entityManager.find(Product.class, id);
        if (deletedProduct != null) {
            entityManager.remove(deletedProduct);
        }
    }
}
