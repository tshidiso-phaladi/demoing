package za.co.study.casetshidiso.demoing.domain.model.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product constructProduct() {
        return new Product("test product", BigDecimal.valueOf(1234));
    }

    private Product constructProductWithZeroPrice() {
        return new Product("test product", BigDecimal.ZERO);
    }

    @Test
    public void testProductConstruction() {
        Product product = constructProduct();

        assertThat(product.getName()).isEqualTo("test product");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(1234));
        assertThat(product.getId()).isNotNull();
    }

    @Test
    public void testProductConstructionWithZeroPrice() {
        Product product = constructProductWithZeroPrice();

        assertThat(product.getName()).isEqualTo("test product");
        assertThat(product.getPrice()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void testProductConstructionWithNullName() {
        Product product = new Product(null, BigDecimal.ZERO);

        assertThat(product.getName()).isNull();
        assertThat(product.getPrice()).isNotNull();
    }
}
