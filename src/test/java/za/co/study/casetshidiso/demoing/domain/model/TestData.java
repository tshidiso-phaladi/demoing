package za.co.study.casetshidiso.demoing.domain.model;

import za.co.study.casetshidiso.demoing.domain.model.product.Product;
import za.co.study.casetshidiso.demoing.domain.model.user.User;

import java.math.BigDecimal;

public class TestData {
    public static final User USER1 = new User("test1 user", "test1.user@testing.com");
    public static final User USER2 = new User("test2 user", "test2.user@testing.com");
    public static final User USER3 = new User("test3 user", "test3.user@testing.com");
    public static final User USER4 = new User("test4 user", "test4.user@testing.com");

    public static final Product PRODUCT1 = new Product("test5 product", BigDecimal.ZERO);
    public static final Product PRODUCT2 = new Product("test5 product", BigDecimal.valueOf(1232));
    public static final Product PRODUCT3 = new Product("test5 product", BigDecimal.valueOf(213.12));
    public static final Product PRODUCT4 = new Product("test5 product", BigDecimal.valueOf(3434));
}
