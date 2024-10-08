package za.co.study.casetshidiso.demoing.domain.model.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testProductListSizeEqualToFour(){
        List<Product> productList = getProducts();

        assertThat(productList.size()).isEqualTo(4);
    }

    @Test
    public void testFindAllListSizeGreaterThanZero() {
        List<Product> productList = getProducts();

        assertThat(productList.size()).isGreaterThan(0);
    }

    @Test
    public void testProductInstance(){
        Product product = getProduct();
        assertThat(product).isInstanceOf(Product.class);
    }

    private List<Product> getProducts() {
        return entityManager
                .createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    private Product getProduct() {
        return entityManager
                .createQuery("select p from Product p", Product.class)
                .getSingleResult();
    }
}