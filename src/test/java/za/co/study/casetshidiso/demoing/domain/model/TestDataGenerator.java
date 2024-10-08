package za.co.study.casetshidiso.demoing.domain.model;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class TestDataGenerator {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void loadTestData() {

        unLoadAllData();
        loadUserTestData();
        loadProductTestData();
    }

    private void unLoadAllData() {
        entityManager.createQuery("Delete from User").executeUpdate();
        entityManager.createQuery("Delete from Product").executeUpdate();
    }

    private void loadProductTestData() {
        entityManager.persist(TestData.PRODUCT1);
        entityManager.persist(TestData.PRODUCT2);
        entityManager.persist(TestData.PRODUCT3);
        entityManager.persist(TestData.PRODUCT4);
    }

    private void loadUserTestData() {
        entityManager.persist(TestData.USER1);
        entityManager.persist(TestData.USER2);
        entityManager.persist(TestData.USER3);
        entityManager.persist(TestData.USER4);
    }
}